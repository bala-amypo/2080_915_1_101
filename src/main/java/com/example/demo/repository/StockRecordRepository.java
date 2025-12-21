package com.example.demo.repository;

import com.example.demo.model.StockRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface StockRecordRepository extends JpaRepository<StockRecord, Long> { // [cite: 115]
    List<StockRecord> findByProductId(Long productId); // [cite: 117]
    List<StockRecord> findByWarehouseId(Long warehouseId); // [cite: 118]
    Optional<StockRecord> findByProductIdAndWarehouseId(Long productId, Long warehouseId);
}