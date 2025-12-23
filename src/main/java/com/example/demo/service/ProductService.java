package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {

    // ✅ must exist (used by controller & tests)
    Product save(Product product);

    // ✅ must exist
    Product getById(Long id);

    // ✅ must exist
    List<Product> getAll();
}
