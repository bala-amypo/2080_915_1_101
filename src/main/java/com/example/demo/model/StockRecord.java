package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_records")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 42]

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; // [cite: 43]

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse; // [cite: 44]

    private Integer currentQuantity; // Must be >= 0 [cite: 45, 48]

    private Integer reorderThreshold; // Must be > 0 [cite: 46, 48]

    private LocalDateTime lastUpdated; // [cite: 47]
}