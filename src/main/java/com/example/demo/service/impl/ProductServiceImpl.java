package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.exception.*;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.*;

@Service("productServiceImpl")
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public Product createProduct(Product p) {
        if (p.getProductName() == null || p.getProductName().isBlank())
            throw new IllegalArgumentException("Invalid product");
        repo.findBySku(p.getSku()).ifPresent(x -> {
            throw new IllegalArgumentException("SKU exists");
        });
        p.setCreatedAt(LocalDateTime.now());
        return repo.save(p);
    }

    public Product getProduct(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }
}
