package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class StockRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer currentQuantity;   // Matches getCurrentQuantity()
    private Integer reorderThreshold;  // Matches getReorderThreshold()
    private LocalDateTime lastUpdated;

    @ManyToOne
    private Product product;   // Matches setProduct()
    @ManyToOne
    private Warehouse warehouse; // Matches setWarehouse()
}