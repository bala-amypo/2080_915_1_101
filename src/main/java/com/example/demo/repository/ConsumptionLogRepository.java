package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.example.demo.model.*;
public interface ConsumptionLogRepository extends JpaRepository<ConsumptionLog, Long> {
    List<ConsumptionLog> findByStockRecordId(Long id);
    List<ConsumptionLog> findByStockRecordIdAndConsumedDateBetween(Long id, java.time.LocalDate s, java.time.LocalDate e);
    List<ConsumptionLog> findByStockRecordIdOrderByConsumedDateDesc(Long id);
}
