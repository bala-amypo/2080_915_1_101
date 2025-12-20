package com.example.demo.service;

import com.example.demo.model.ConsumptionLog;
import java.util.List;

public interface ConsumptionLogService {
    ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log); [cite: 186]
    List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId); [cite: 187]
    ConsumptionLog getLog(Long id); [cite: 188]
}