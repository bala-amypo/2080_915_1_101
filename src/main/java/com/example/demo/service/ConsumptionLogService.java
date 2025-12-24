package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.StockRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConsumptionLogService {
    @Autowired
    private ConsumptionLogRepository consumptionLogRepository;
    @Autowired
    private StockRecordRepository stockRecordRepository;

    public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log) {
        if(log.getConsumedDate() != null && log.getConsumedDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("consumedDate cannot be future");
        }
        
        StockRecord sr = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock Record not found"));

        log.setStockRecord(sr);
        if(log.getConsumedDate() == null) {
            log.setConsumedDate(LocalDate.now());
        }

        // Deduct from stock
        int newQty = sr.getCurrentQuantity() - log.getConsumedQuantity();
        if(newQty < 0) newQty = 0; // Prevent negative stock for this logic
        sr.setCurrentQuantity(newQty);
        stockRecordRepository.save(sr);

        return consumptionLogRepository.save(log);
    }

    public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
        return consumptionLogRepository.findByStockRecordId(stockRecordId);
    }
}