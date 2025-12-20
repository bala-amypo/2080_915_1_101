package com.example.demo.service;

import com.example.demo.model.Product;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product); // [cite: 153]
    Product getProduct(Long id); // [cite: 154]
    List<Product> getAllProducts(); // [cite: 155]
}