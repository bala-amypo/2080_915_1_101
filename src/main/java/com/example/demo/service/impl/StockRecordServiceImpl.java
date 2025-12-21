package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.StockRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockRecordServiceImpl implements StockRecordService {
    private final StockRecordRepository repository;
    private final ProductRepository productRepo;
    private final WarehouseRepository warehouseRepo;

    @Override
    public StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord record) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found")); // [cite: 178]
        Warehouse warehouse = warehouseRepo.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found")); // [cite: 178]

        if (repository.findByProductIdAndWarehouseId(productId, warehouseId).isPresent())
            throw new IllegalArgumentException("StockRecord already exists"); // [cite: 179]

        if (record.getCurrentQuantity() < 0) throw new IllegalArgumentException("currentQuantity >= 0"); // [cite: 180]
        if (record.getReorderThreshold() <= 0) throw new IllegalArgumentException("reorderThreshold > 0"); // [cite: 180]

        record.setProduct(product);
        record.setWarehouse(warehouse);
        record.setLastUpdated(LocalDateTime.now()); // [cite: 180]
        return repository.save(record);
    }

    @Override
    public StockRecord getStockRecord(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("StockRecord not found")); // [cite: 181]
    }

    @Override
    public List<StockRecord> getRecordsBy_product(Long productId) {
        return repository.findByProductId(productId); // [cite: 182]
    }

    @Override
    public List<StockRecord> getRecordsByWarehouse(Long warehouseId) {
        return repository.findByWarehouseId(warehouseId); // [cite: 183]
    }
}