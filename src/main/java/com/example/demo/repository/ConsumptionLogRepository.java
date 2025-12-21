package com.example.demo.repository;

import com.example.demo.model.ConsumptionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumptionLogRepository extends JpaRepository<ConsumptionLog, Long> {

    // ✅ Correct derived query based on entity relationship
    List<ConsumptionLog> findByStockRecord_Id(Long stockRecordId);
}
