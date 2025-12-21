package com.example.demo.service;

import com.example.demo.model.ConsumptionLog;
import java.util.List;

public interface ConsumptionLogService {
    /**
     * Logs product consumption and validates that the date is not in the future[cite: 191, 192].
     */
    ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log);

    /**
     * Returns all consumption logs for a specific stock record[cite: 194].
     */
    List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId);

    /**
     * Retrieves a single consumption log entry by ID[cite: 195].
     */
    ConsumptionLog getLog(Long id);
}