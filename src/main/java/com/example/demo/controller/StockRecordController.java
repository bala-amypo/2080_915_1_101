package com.example.demo.controller;

import com.example.demo.model.StockRecord;
import com.example.demo.service.StockRecordService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockRecordController {

    private final StockRecordService service;

    public StockRecordController(StockRecordService service) {
        this.service = service;
    }

    /* ===== STRING ===== */
    @GetMapping("/{id}")
    public StockRecord getStockRecord(@PathVariable String id) {
        return service.getStockRecord(id);
    }

    /* ===== LONG (FOR TESTS – NO MAPPING) ===== */
    public StockRecord getStockRecord(long id) {
        return service.getStockRecord(id);
    }

    /* ===== PRODUCT ===== */
    public List<StockRecord> getByProduct(String productId) {
        return service.getRecordsBy_product(productId);
    }

    public List<StockRecord> getByProduct(long productId) {
        return service.getRecordsBy_product(productId);
    }

    /* ===== WAREHOUSE ===== */
    public List<StockRecord> getByWarehouse(String warehouseId) {
        return service.getRecordsByWarehouse(warehouseId);
    }

    public List<StockRecord> getByWarehouse(long warehouseId) {
        return service.getRecordsByWarehouse(warehouseId);
    }
}
