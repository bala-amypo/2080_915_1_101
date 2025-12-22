package com.example.demo.service;

import com.example.demo.model.StockRecord;
import java.util.List;

public interface StockRecordService {

    /* ===== CREATE ===== */
    StockRecord createStockRecord(String productId, String warehouseId, StockRecord record);

    /* ===== READ ===== */
    StockRecord getStockRecord(String id);
    StockRecord getStockRecord(Long id);

    List<StockRecord> getRecordsBy_product(String productId);
    List<StockRecord> getRecordsBy_product(Long productId);

    List<StockRecord> getRecordsByWarehouse(String warehouseId);
    List<StockRecord> getRecordsByWarehouse(Long warehouseId);

    /* ===== UPDATE ===== */
    StockRecord updateStock(String id, StockRecord updated);
}
