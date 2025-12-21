package com.example.demo.service.impl;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumptionLogServiceImpl implements ConsumptionLogService {

    private final ConsumptionLogRepository consumptionLogRepository;

    public ConsumptionLogServiceImpl(ConsumptionLogRepository consumptionLogRepository) {
        this.consumptionLogRepository = consumptionLogRepository;
    }

    @Override
    public ConsumptionLog saveLog(ConsumptionLog log) {
        return consumptionLogRepository.save(log);
    }

    @Override
    public List<ConsumptionLog> getLogsByStockRecordId(Long stockRecordId) {
        return consumptionLogRepository.findByStockRecord_Id(stockRecordId);
    }
}
