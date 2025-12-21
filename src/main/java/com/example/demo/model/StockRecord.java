package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_records", uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "warehouse_id"})}) // [cite: 40, 49]
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class StockRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 42]
    @ManyToOne @JoinColumn(name = "product_id")
    private Product product; // [cite: 43]
    @ManyToOne @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse; // [cite: 44]
    private Integer currentQuantity; // [cite: 45]
    private Integer reorderThreshold; // [cite: 46]
    private LocalDateTime lastUpdated; // [cite: 47]
}