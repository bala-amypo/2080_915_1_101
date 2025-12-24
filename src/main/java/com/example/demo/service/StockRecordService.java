package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.StockRecord;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StockRecordService {
    @Autowired
    private StockRecordRepository stockRecordRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    public StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord stockRecord) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));

        Optional<StockRecord> existing = stockRecordRepository.findByProductAndWarehouse(product, warehouse);
        if(existing.isPresent()) {
            throw new IllegalArgumentException("StockRecord already exists");
        }

        stockRecord.setProduct(product);
        stockRecord.setWarehouse(warehouse);
        stockRecord.setLastUpdated(LocalDateTime.now());
        return stockRecordRepository.save(stockRecord);
    }

    public StockRecord getStockRecord(Long id) {
        return stockRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock Record not found"));
    }

    public List<StockRecord> getRecordsBy_product(Long productId) {
        return stockRecordRepository.findByProductId(productId);
    }
    
    public void updateQuantity(StockRecord sr, int newQty) {
        sr.setCurrentQuantity(newQty);
        sr.setLastUpdated(LocalDateTime.now());
        stockRecordRepository.save(sr);
    }
}