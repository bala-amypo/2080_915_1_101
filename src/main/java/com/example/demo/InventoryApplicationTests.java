package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.security.JwtProvider; // Fixed: Matches package rule [cite: 12]
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InventoryApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private StockRecordRepository stockRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider; // Fixed symbol 

    @Test
    void contextLoads() {
        // Basic context load test
    }

    @Test
    void testJwtGeneration() {
        String email = "test@example.com";
        Set<String> roles = Collections.singleton("ROLE_USER");
        Long userId = 1L;

        String token = jwtProvider.generateToken(email, roles.stream().toList(), userId);
        assertNotNull(token);
        assertTrue(jwtProvider.validateToken(token));
        assertEquals(email, jwtProvider.getEmailFromToken(token));
    }

    @Test
    void testDatabaseRelationships() {
        Product product = Product.builder()
                .productName("Test Product")
                .sku("SKU-999")
                .category("Test")
                .createdAt(LocalDateTime.now())
                .build();
        productRepository.save(product);

        Warehouse warehouse = Warehouse.builder()
                .warehouseName("Main Test Warehouse")
                .location("Test Location")
                .createdAt(LocalDateTime.now())
                .build();
        warehouseRepository.save(warehouse);

        StockRecord record = StockRecord.builder()
                .product(product)
                .warehouse(warehouse)
                .currentQuantity(100)
                .reorderThreshold(20)
                .lastUpdated(LocalDateTime.now())
                .build();
        
        StockRecord saved = stockRecordRepository.save(record);
        assertNotNull(saved.getId());
        assertEquals(100, saved.getCurrentQuantity());
    }
}