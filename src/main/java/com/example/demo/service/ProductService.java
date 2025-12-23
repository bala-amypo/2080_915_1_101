package com.example.demo.service;

import com.example.demo.model.Product;
import java.util.List;

public interface ProductService {

    // ✅ REQUIRED BY TESTS
    Product createProduct(Product product);

    // keep these (used elsewhere)
    Product save(Product product);

    Product getById(Long id);

    List<Product> getAll();
}
