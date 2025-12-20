package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 34]

    @Column(unique = true, nullable = false)
    private String warehouseName; // Must be unique [cite: 35, 38]

    @Column(nullable = false)
    private String location; // Must not be empty [cite: 36, 38]

    private LocalDateTime createdAt; // [cite: 37]
}