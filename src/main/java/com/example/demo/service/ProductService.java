package com.example.demo.service;

import com.example.demo.model.Product;
import java.util.List;

public interface ProductService {

    // 🔹 REQUIRED BY TESTS
    Product createProduct(Product product);

    Product getProduct(Long id);

    List<Product> getAllProducts();

    // 🔹 KEEP existing methods (used elsewhere)
    Product save(Product product);

    Product getById(Long id);

    List<Product> getAll();
}
