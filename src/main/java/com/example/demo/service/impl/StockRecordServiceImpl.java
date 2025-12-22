package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.StockRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StockRecordServiceImpl implements StockRecordService {

    private final StockRecordRepository stockRecordRepository;

    public StockRecordServiceImpl(StockRecordRepository stockRecordRepository) {
        this.stockRecordRepository = stockRecordRepository;
    }

    /* ================= CREATE ================= */

    @Override
    public StockRecord createStockRecord(String productId, String warehouseId, StockRecord record) {
        record.setLastUpdated(LocalDate.now());
        return stockRecordRepository.save(record);
    }

    /* ================= READ ================= */

    @Override
    public StockRecord getStockRecord(String id) {
        return getStockRecord(Long.parseLong(id));
    }

    @Override
    public StockRecord getStockRecord(Long id) {
        return stockRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));
    }

    @Override
    public List<StockRecord> getRecordsBy_product(String productId) {
        return getRecordsBy_product(Long.parseLong(productId));
    }

    @Override
    public List<StockRecord> getRecordsBy_product(Long productId) {
        return stockRecordRepository.findByProductId(productId);
    }

    @Override
    public List<StockRecord> getRecordsByWarehouse(String warehouseId) {
        return getRecordsByWarehouse(Long.parseLong(warehouseId));
    }

    @Override
    public List<StockRecord> getRecordsByWarehouse(Long warehouseId) {
        return stockRecordRepository.findByWarehouseId(warehouseId);
    }

    /* ================= UPDATE ================= */

    @Override
    public StockRecord updateStock(String id, StockRecord updated) {
        StockRecord record = getStockRecord(id);

        record.setCurrentQuantity(updated.getCurrentQuantity());
        record.setReorderThreshold(updated.getReorderThreshold());
        record.setLastUpdated(LocalDate.now());

        return stockRecordRepository.save(record);
    }
}
