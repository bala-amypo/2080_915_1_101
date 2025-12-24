package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.example.demo.model.*;
public interface StockRecordRepository extends JpaRepository<StockRecord, Long> {
    List<StockRecord> findByProductId(Long productId);
    List<StockRecord> findByWarehouseId(Long warehouseId);
}
