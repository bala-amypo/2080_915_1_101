package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConsumptionLogServiceImpl implements ConsumptionLogService {

    private final ConsumptionLogRepository logRepository;
    private final StockRecordRepository stockRecordRepository;

    public ConsumptionLogServiceImpl(
            ConsumptionLogRepository logRepository,
            StockRecordRepository stockRecordRepository) {
        this.logRepository = logRepository;
        this.stockRecordRepository = stockRecordRepository;
    }

    @Override
    public ConsumptionLog logConsumption(String stockRecordId, ConsumptionLog log) {

        Long id = Long.parseLong(stockRecordId);

        StockRecord record = stockRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        if (log.getConsumedQuantity() <= 0) {
            throw new IllegalArgumentException("consumedQuantity must be > 0");
        }

        if (log.getConsumedDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("consumedDate cannot be future");
        }

        log.setStockRecord(record);
        return logRepository.save(log);
    }

    @Override
    public List<ConsumptionLog> getLogsByStockRecord(String stockRecordId) {
        return logRepository.findByStockRecordId(Long.parseLong(stockRecordId));
    }

    @Override
    public ConsumptionLog getLog(String id) {
        return logRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new ResourceNotFoundException("ConsumptionLog not found"));
    }
}
