package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ConsumptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private int consumedQuantity;
    private LocalDate date;

    public ConsumptionLog() {}

    public Long getId() { return id; }
    public Product getProduct() { return product; }
    public int getConsumedQuantity() { return consumedQuantity; }
    public LocalDate getDate() { return date; }

    public void setId(Long id) { this.id = id; }
    public void setProduct(Product product) { this.product = product; }
    public void setConsumedQuantity(int consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
    }
    public void setDate(LocalDate date) { this.date = date; }
}
