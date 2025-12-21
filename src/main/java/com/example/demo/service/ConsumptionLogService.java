package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consumption_logs")
public class ConsumptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Relationship with StockRecord
    @ManyToOne
    @JoinColumn(name = "stock_record_id", nullable = false)
    private StockRecord stockRecord;

    @Column(nullable = false)
    private int quantityUsed;

    @Column(nullable = false)
    private LocalDateTime consumedAt;

    public ConsumptionLog() {
    }

    public ConsumptionLog(StockRecord stockRecord, int quantityUsed, LocalDateTime consumedAt) {
        this.stockRecord = stockRecord;
        this.quantityUsed = quantityUsed;
        this.consumedAt = consumedAt;
    }

    public Long getId() {
        return id;
    }

    public StockRecord getStockRecord() {
        return stockRecord;
    }

    public void setStockRecord(StockRecord stockRecord) {
        this.stockRecord = stockRecord;
    }

    public int getQuantityUsed() {
        return quantityUsed;
    }

    public void setQuantityUsed(int quantityUsed) {
        this.quantityUsed = quantityUsed;
    }

    public LocalDateTime getConsumedAt() {
        return consumedAt;
    }

    public void setConsumedAt(LocalDateTime consumedAt) {
        this.consumedAt = consumedAt;
    }
}
