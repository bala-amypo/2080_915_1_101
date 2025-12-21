package com.example.demo.service;

import com.example.demo.model.ConsumptionLog;

import java.util.List;

public interface ConsumptionLogService {

    ConsumptionLog saveLog(ConsumptionLog log);

    List<ConsumptionLog> getLogsByStockRecordId(Long stockRecordId);
}
