package com.example.demo.repository;

import com.example.demo.model.ConsumptionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsumptionLogRepository
        extends JpaRepository<ConsumptionLog, Long> {

    List<ConsumptionLog> findByStockRecordId(Long stockRecordId);
}
package com.example.demo.repository;

import com.example.demo.model.ConsumptionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConsumptionLogRepository extends JpaRepository<ConsumptionLog, Long> {
    // Fetches all logs for a given stock record to calculate usage [cite: 121]
    List<ConsumptionLog> findByStockRecordId(Long stockRecordId);
}