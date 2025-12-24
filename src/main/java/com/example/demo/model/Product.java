package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank; // Added import
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product Name is required") // Added validation
    private String productName;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "SKU is required") // Added validation
    private String sku;

    private String category;
    
    private LocalDateTime createdAt;
}