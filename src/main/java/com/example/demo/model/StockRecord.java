package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class StockRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int currentQuantity;
    private int reorderThreshold;
    private LocalDateTime lastUpdated;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Warehouse warehouse;

    public int getCurrentQuantity() { return currentQuantity; }
    public int getReorderThreshold() { return reorderThreshold; }

    public void setProduct(Product product) { this.product = product; }
    public void setWarehouse(Warehouse warehouse) { this.warehouse = warehouse; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
