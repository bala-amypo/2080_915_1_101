package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class StockRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ===== INVENTORY FIELDS (REQUIRED BY SERVICES & TESTS) ===== */

    private int quantity;              // used by PredictionService
    private int currentQuantity;       // used by StockRecordServiceImpl
    private int reorderThreshold;      // used by StockRecordServiceImpl

    private LocalDate lastUpdated;      // used by tests
    private LocalDateTime updatedAt;    // used internally (safe to keep)

    /* ===== RELATIONS ===== */

    @ManyToOne
    private Product product;

    @ManyToOne
    private Warehouse warehouse;

    @OneToMany(mappedBy = "stockRecord", cascade = CascadeType.ALL)
    private List<ConsumptionLog> consumptionLogs;

    /* ================= GETTERS & SETTERS ================= */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /* ===== Quantity (Prediction) ===== */
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.currentQuantity = quantity; // keep in sync
    }

    /* ===== Current Quantity ===== */
    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    /* ===== Reorder Threshold ===== */
    public int getReorderThreshold() {
        return reorderThreshold;
    }

    public void setReorderThreshold(int reorderThreshold) {
        this.reorderThreshold = reorderThreshold;
    }

    /* ===== Dates ===== */
    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        this.lastUpdated = updatedAt.toLocalDate(); // FIX for LocalDate error
    }

    /* ===== Relations ===== */
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public List<ConsumptionLog> getConsumptionLogs() {
        return consumptionLogs;
    }

    public void setConsumptionLogs(List<ConsumptionLog> consumptionLogs) {
        this.consumptionLogs = consumptionLogs;
    }
}
