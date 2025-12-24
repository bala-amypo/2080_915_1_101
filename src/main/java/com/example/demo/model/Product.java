package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(
    name = "products",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "sku")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name must not be blank")
    @Column(nullable = false)
    private String productName;

    @NotBlank(message = "SKU must not be blank")
    @Column(nullable = false, unique = true)
    private String sku;

    private String description;
}
