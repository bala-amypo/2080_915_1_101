package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String sku;
    private String category;
    private LocalDateTime createdAt = LocalDateTime.now();

    // getters & setters
    public Long getId() { return id; }
    public String getProductName() { return productName; }
    public String getSku() { return sku; }
    public String getCategory() { return category; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setSku(String sku) { this.sku = sku; }
    public void setCategory(String category) { this.category = category; }
}
