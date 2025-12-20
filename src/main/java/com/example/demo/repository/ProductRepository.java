package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Used for checking SKU uniqueness during product creation
    Optional<Product> findBySku(String sku); 
}