package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class StockRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Warehouse warehouse;

    private int currentQuantity;
    private int reorderThreshold;

    public Long getId() { return id; }
    public Product getProduct() { return product; }
    public Warehouse getWarehouse() { return warehouse; }
    public int getCurrentQuantity() { return currentQuantity; }
    public int getReorderThreshold() { return reorderThreshold; }

    public void setId(Long id) { this.id = id; }
    public void setProduct(Product product) { this.product = product; }
    public void setWarehouse(Warehouse warehouse) { this.warehouse = warehouse; }
    public void setCurrentQuantity(int currentQuantity) { this.currentQuantity = currentQuantity; }
    public void setReorderThreshold(int reorderThreshold) { this.reorderThreshold = reorderThreshold; }
}
