package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ConsumptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int consumedQuantity;
    private LocalDate consumedDate;

    @ManyToOne
    private StockRecord stockRecord;

    public int getConsumedQuantity() { return consumedQuantity; }
    public LocalDate getConsumedDate() { return consumedDate; }
    public void setStockRecord(StockRecord stockRecord) { this.stockRecord = stockRecord; }
}
