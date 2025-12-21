package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service // [cite: 149]
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        if (product.getProductName() == null || product.getProductName().isEmpty()) 
            throw new IllegalArgumentException("Product name cannot be empty"); // [cite: 157]
        if (productRepository.findBySku(product.getSku()).isPresent()) 
            throw new IllegalArgumentException("SKU already exists"); // [cite: 157]
        product.setCreatedAt(LocalDateTime.now()); // [cite: 157]
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found")); // [cite: 158]
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll(); // [cite: 159]
    }
}