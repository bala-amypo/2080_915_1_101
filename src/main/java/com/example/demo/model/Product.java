package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 25]

    @Column(nullable = false)
    private String productName; // Must not be empty [cite: 26, 30]

    @Column(unique = true, nullable = false)
    private String sku; // Must be unique [cite: 27, 30]

    private String category; // [cite: 28]

    private LocalDateTime createdAt; // [cite: 29]
}