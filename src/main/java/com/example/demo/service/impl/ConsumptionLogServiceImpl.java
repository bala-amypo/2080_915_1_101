package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConsumptionLogServiceImpl implements ConsumptionLogService {

    @Autowired
    private ConsumptionLogRepository consumptionLogRepository;
    @Autowired
    private StockRecordRepository stockRecordRepository;

    @Override
    public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log) {
        StockRecord stockRecord = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        if (log.getConsumedDate() != null && log.getConsumedDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("consumedDate cannot be future");
        }
        
        if (log.getConsumedQuantity() == null || log.getConsumedQuantity() <= 0) {
            // Default check
        }

        int current = stockRecord.getCurrentQuantity() != null ? stockRecord.getCurrentQuantity() : 0;
        int consumed = log.getConsumedQuantity() != null ? log.getConsumedQuantity() : 0;
        int newQty = Math.max(0, current - consumed);
        
        stockRecord.setCurrentQuantity(newQty);
        stockRecordRepository.save(stockRecord);

        log.setStockRecord(stockRecord);
        return consumptionLogRepository.save(log);
    }

    @Override
    public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
        return consumptionLogRepository.findByStockRecordId(stockRecordId);
    }
}