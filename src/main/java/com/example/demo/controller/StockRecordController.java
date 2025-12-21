package com.example.demo.controller;

import com.example.demo.model.StockRecord;
import com.example.demo.service.StockRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/stocks") // [cite: 241]
@RequiredArgsConstructor
public class StockRecordController {
    private final StockRecordService service;

    @PostMapping("/{productId}/{warehouseId}") // [cite: 243]
    public StockRecord create(@PathVariable Long productId, @PathVariable Long warehouseId, @RequestBody StockRecord r) {
        return service.createStockRecord(productId, warehouseId, r); // [cite: 246]
    }

    @GetMapping("/product/{productId}") // [cite: 247]
    public List<StockRecord> getByP(@PathVariable Long productId) { return service.getRecordsBy_product(productId); }
}