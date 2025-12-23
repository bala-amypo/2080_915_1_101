package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ConsumptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StockRecord stockRecord;

    private int consumedQuantity;
    private LocalDate consumedDate;

    public Long getId() { return id; }
    public StockRecord getStockRecord() { return stockRecord; }
    public int getConsumedQuantity() { return consumedQuantity; }
    public LocalDate getConsumedDate() { return consumedDate; }

    public void setId(Long id) { this.id = id; }
    public void setStockRecord(StockRecord stockRecord) { this.stockRecord = stockRecord; }
    public void setConsumedQuantity(int consumedQuantity) { this.consumedQuantity = consumedQuantity; }
    public void setConsumedDate(LocalDate consumedDate) { this.consumedDate = consumedDate; }
}
