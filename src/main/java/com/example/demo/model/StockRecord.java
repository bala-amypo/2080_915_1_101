package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private int currentQuantity;
    private int reorderThreshold;

    private LocalDate lastUpdated;
    private LocalDateTime updatedAt;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Warehouse warehouse;

    @OneToMany(mappedBy = "stockRecord", cascade = CascadeType.ALL)
    private List<ConsumptionLog> consumptionLogs;

    /* ===== SAFETY SYNC ===== */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        this.lastUpdated = updatedAt.toLocalDate();
    }
}
