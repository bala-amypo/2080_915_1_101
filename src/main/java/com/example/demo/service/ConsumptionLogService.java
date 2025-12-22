package com.example.demo.service;

import com.example.demo.model.ConsumptionLog;
import java.util.List;

public interface ConsumptionLogService {

    ConsumptionLog logConsumption(String stockRecordId, ConsumptionLog log);

    List<ConsumptionLog> getLogsByStockRecord(String stockRecordId);

    ConsumptionLog getLog(String id);
}
