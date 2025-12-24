package com.example.demo.service;

import java.util.*;
import java.time.LocalDate;
import com.example.demo.model.*;
public interface ConsumptionLogService {
    ConsumptionLog logConsumption(Long stockId, ConsumptionLog log);
    List<ConsumptionLog> getLogsByStockRecord(Long stockId);
    ConsumptionLog getLog(Long id);
}
