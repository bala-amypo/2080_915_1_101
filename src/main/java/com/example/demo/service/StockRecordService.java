package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.StockRecord;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockRecordService {
    private final StockRecordRepository stockRecordRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord record) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));

        if (stockRecordRepository.findByProductIdAndWarehouseId(productId, warehouseId).isPresent()) {
            throw new IllegalArgumentException("StockRecord already exists"); // Exact keyword required
        }
        if (record.getCurrentQuantity() < 0) {
            throw new IllegalArgumentException("Quantity must be >= 0");
        }
        if (record.getReorderThreshold() <= 0) {
            throw new IllegalArgumentException("Reorder Threshold must be > 0");
        }

        record.setProduct(product);
        record.setWarehouse(warehouse);
        record.setLastUpdated(LocalDateTime.now());
        return stockRecordRepository.save(record);
    }

    public StockRecord getStockRecord(Long id) {
        return stockRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));
    }

    // Exact naming required by requirement 6.3 / test constraint
    public List<StockRecord> getRecordsBy_product(Long productId) {
        return stockRecordRepository.findByProductId(productId);
    }

    public List<StockRecord> getRecordsByWarehouse(Long warehouseId) {
        return stockRecordRepository.findByWarehouseId(warehouseId);
    }
}