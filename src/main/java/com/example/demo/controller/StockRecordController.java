package com.example.demo.controller;

import com.example.demo.model.StockRecord;
import com.example.demo.service.StockRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockRecordController {
    private final StockRecordService stockRecordService;

    @PostMapping("/{productId}/{warehouseId}")
    public ResponseEntity<StockRecord> createStockRecord(@PathVariable Long productId,
                                                         @PathVariable Long warehouseId,
                                                         @RequestBody StockRecord record) {
        return ResponseEntity.ok(stockRecordService.createStockRecord(productId, warehouseId, record));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<StockRecord>> getRecordsByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(stockRecordService.getRecordsBy_product(productId));
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<StockRecord>> getRecordsByWarehouse(@PathVariable Long warehouseId) {
        return ResponseEntity.ok(stockRecordService.getRecordsByWarehouse(warehouseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockRecord> getStockRecord(@PathVariable Long id) {
        return ResponseEntity.ok(stockRecordService.getStockRecord(id));
    }
}