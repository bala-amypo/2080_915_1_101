package com.example.demo.service;

import com.example.demo.model.ConsumptionLog;
import java.util.List;

public interface ConsumptionLogService {

    ConsumptionLog getLog(String id);
    ConsumptionLog getLog(long id);

    List<ConsumptionLog> getLogsByStockRecord(String stockId);
    List<ConsumptionLog> getLogsByStockRecord(long stockId);
}
