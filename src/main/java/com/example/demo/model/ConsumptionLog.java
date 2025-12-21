package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ConsumptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantityUsed;

    private LocalDate consumptionDate;

    @ManyToOne
    @JoinColumn(name = "stock_record_id", nullable = false)
    private StockRecord stockRecord;

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public int getQuantityUsed() {
        return quantityUsed;
    }

    public void setQuantityUsed(int quantityUsed) {
        this.quantityUsed = quantityUsed;
    }

    public LocalDate getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(LocalDate consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public StockRecord getStockRecord() {
        return stockRecord;
    }

    public void setStockRecord(StockRecord stockRecord) {
        this.stockRecord = stockRecord;
    }
}
