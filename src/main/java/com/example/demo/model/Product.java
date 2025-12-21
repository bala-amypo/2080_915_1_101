package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products") // [cite: 23]
@Data @Builder @NoArgsConstructor @AllArgsConstructor // [cite: 21]
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 25]
    private String productName; // [cite: 26]
    @Column(unique = true)
    private String sku; // [cite: 27]
    private String category; // [cite: 28]
    private LocalDateTime createdAt; // [cite: 29]
}