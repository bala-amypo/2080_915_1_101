package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ConsumptionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumptionLogServiceImpl implements ConsumptionLogService {
    private final ConsumptionLogRepository repository;
    private final StockRecordRepository stockRepo;

    @Override
    public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log) {
        StockRecord record = stockRepo.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found")); // [cite: 190]
        
        if (log.getConsumedQuantity() <= 0) throw new IllegalArgumentException("consumedQuantity > 0"); // [cite: 191]
        log.setConsumedDate(java.time.LocalDateTime.now()); // CORRECT 
            throw new IllegalArgumentException("consumedDate cannot be future"); // [cite: 192]

        log.setStockRecord(record); // [cite: 193]
        return repository.save(log);
    }

    @Override
    public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
        return repository.findByStockRecordId(stockRecordId); // [cite: 194]
    }

    @Override
    public ConsumptionLog getLog(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ConsumptionLog not found")); // [cite: 195]
    }
}