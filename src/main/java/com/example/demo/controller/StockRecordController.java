package com.example.demo.controller;

import com.example.demo.model.StockRecord;
import com.example.demo.service.StockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockRecordController {
    @Autowired
    private StockRecordService stockRecordService;

    @PostMapping("/{productId}/{warehouseId}")
    public ResponseEntity<StockRecord> createStock(@PathVariable Long productId, 
                                                   @PathVariable Long warehouseId,
                                                   @RequestBody StockRecord record) {
        return ResponseEntity.ok(stockRecordService.createStockRecord(productId, warehouseId, record));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockRecord> getStock(@PathVariable Long id) {
        return ResponseEntity.ok(stockRecordService.getStockRecord(id));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<StockRecord>> getByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(stockRecordService.getRecordsBy_product(productId));
    }
}