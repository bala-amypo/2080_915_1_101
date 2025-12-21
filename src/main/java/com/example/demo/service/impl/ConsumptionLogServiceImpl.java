package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ConsumptionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumptionLogServiceImpl implements ConsumptionLogService {
    private final ConsumptionLogRepository repository;
    private final StockRecordRepository stockRepo;

    @Override
    public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log) {
        StockRecord record = stockRepo.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));
        
        if (log.getConsumedQuantity() == null || log.getConsumedQuantity() <= 0) {
            throw new IllegalArgumentException("consumedQuantity must be greater than 0");
        }

        // Set the date and the relationship
        log.setConsumedDate(LocalDateTime.now()); 
        log.setStockRecord(record); 

        return repository.save(log);
    }

    @Override
    public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
        return repository.findByStockRecordId(stockRecordId);
    }

    @Override
    public ConsumptionLog getLog(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ConsumptionLog not found"));
    }
}