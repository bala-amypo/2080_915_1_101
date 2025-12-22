package com.example.demo.service;

import com.example.demo.model.ConsumptionLog;
import java.util.List;

public interface ConsumptionLogService {

    /* ===== Long-based (USED BY TESTS) ===== */
    ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log);

    List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId);

    ConsumptionLog getLog(Long id);

    /* ===== String-based (USED BY OTHER TESTS & CONTROLLERS) ===== */
    ConsumptionLog logConsumption(String stockRecordId, ConsumptionLog log);

    List<ConsumptionLog> getLogsByStockRecord(String stockRecordId);

    ConsumptionLog getLog(String id);
}
