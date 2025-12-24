package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.example.demo.model.*;
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySku(String sku);
}
