package com.example.demo.service;

import com.example.demo.model.StockRecord;
import java.util.List;

public interface StockRecordService {
    /**
     * Creates a stock record for a product-warehouse pair[cite: 172].
     * Throws IllegalArgumentException if a duplicate record exists[cite: 179].
     */
    StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord record);

    /**
     * Retrieves a specific stock record by its ID[cite: 181].
     */
    StockRecord getStockRecord(Long id);

    /**
     * Retrieves all stock records associated with a specific product[cite: 182].
     */
    List<StockRecord> getRecordsBy_product(Long productId);

    /**
     * Retrieves all stock records associated with a specific warehouse[cite: 183].
     */
    List<StockRecord> getRecordsByWarehouse(Long warehouseId);
}