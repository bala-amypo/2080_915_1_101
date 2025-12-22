package com.example.demo.service;

import com.example.demo.model.StockRecord;
import java.util.List;

public interface StockRecordService {

    StockRecord getStockRecord(String id);
    StockRecord getStockRecord(long id);

    List<StockRecord> getRecordsBy_product(String productId);
    List<StockRecord> getRecordsBy_product(long productId);

    List<StockRecord> getRecordsByWarehouse(String warehouseId);
    List<StockRecord> getRecordsByWarehouse(long warehouseId);

    StockRecord updateStock(String id, StockRecord updated);
}
