package com.example.demo.repository;

import com.example.demo.model.StockRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface StockRecordRepository extends JpaRepository<StockRecord, Long> {
    // Required to support fetching records by product [cite: 117]
    List<StockRecord> findByProductId(Long productId);
    
    // Required to support listing by warehouse [cite: 118]
    List<StockRecord> findByWarehouseId(Long warehouseId);
    
    // Helper to identify specific product-warehouse pairs [cite: 179]
    Optional<StockRecord> findByProductIdAndWarehouseId(Long productId, Long warehouseId);
}