package com.example.demo.controller;

import com.example.demo.model.StockRecord;
import com.example.demo.service.StockRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockRecordController {

    private final StockRecordService stockRecordService;

    public StockRecordController(StockRecordService stockRecordService) {
        this.stockRecordService = stockRecordService;
    }

    // CREATE STOCK RECORD
    @PostMapping("/{productId}/{warehouseId}")
    public StockRecord createStockRecord(
            @PathVariable Long productId,
            @PathVariable Long warehouseId,
            @RequestBody StockRecord record) {

        return stockRecordService.createStockRecord(productId, warehouseId, record);
    }

    // GET STOCK RECORD BY ID
    @GetMapping("/{id}")
    public StockRecord getStockRecord(@PathVariable Long id) {
        return stockRecordService.getStockRecord(id);
    }

    // GET STOCK RECORDS BY PRODUCT
    @GetMapping("/product/{productId}")
    public List<StockRecord> getByProduct(@PathVariable Long productId) {
        return stockRecordService.getRecordsBy_product(productId);
    }

    // GET STOCK RECORDS BY WAREHOUSE
    @GetMapping("/warehouse/{warehouseId}")
    public List<StockRecord> getByWarehouse(@PathVariable Long warehouseId) {
        return stockRecordService.getRecordsByWarehouse(warehouseId);
    }
}
