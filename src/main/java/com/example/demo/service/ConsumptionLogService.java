package com.example.demo.service;

import com.example.demo.model.ConsumptionLog;
import java.util.List;

public interface ConsumptionLogService {
    ConsumptionLog save(ConsumptionLog log);
    List<ConsumptionLog> getAll();
}
