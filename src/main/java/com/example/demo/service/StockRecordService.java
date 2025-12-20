package com.example.demo.service;

import com.example.demo.model.StockRecord;
import java.util.List;

public interface StockRecordService {
    StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord record); // [cite: 172]
    StockRecord getStockRecord(Long id); // [cite: 173]
    List<StockRecord> getRecordsBy_product(Long productId); // [cite: 174]
    List<StockRecord> getRecordsByWarehouse(Long warehouseId); // [cite: 175]
}