package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product saveProduct(Product product);

    Optional<Product> getBySku(String sku);

    List<Product> getAllProducts();
}
