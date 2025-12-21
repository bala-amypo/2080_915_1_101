package com.example.demo.service;

import com.example.demo.model.Product;
import java.util.List;

public interface ProductService {
    /**
     * Creates a new product after validating name and SKU uniqueness.
     */
    Product createProduct(Product product);

    /**
     * Retrieves a product by ID or throws ResourceNotFoundException.
     */
    Product getProduct(Long id);

    /**
     * Returns a list of all products in the system.
     */
    List<Product> getAllProducts();
}