package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouses") // [cite: 32]
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Warehouse {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 34]
    @Column(unique = true)
    private String warehouseName; // [cite: 35]
    private String location; // [cite: 36]
    private LocalDateTime createdAt; // [cite: 37]
}