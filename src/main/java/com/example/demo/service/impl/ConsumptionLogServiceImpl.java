package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.ConsumptionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumptionLogServiceImpl implements ConsumptionLogService {
    private final ConsumptionLogRepository logRepository;
    private final StockRecordRepository stockRecordRepository;

    @Override
    public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log) {
        // Load target stock record [cite: 190]
        StockRecord sr = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found")); [cite: 190]

        // Validate consumedQuantity > 0 [cite: 191]
        if (log.getConsumedQuantity() == null || log.getConsumedQuantity() <= 0) {
            throw new IllegalArgumentException("consumedQuantity must be greater than zero"); [cite: 58]
        }

        // Validate consumedDate is not in the future [cite: 191]
        if (log.getConsumedDate() != null && log.getConsumedDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("consumedDate cannot be future"); [cite: 59, 192]
        }

        log.setStockRecord(sr); [cite: 193]
        return logRepository.save(log); [cite: 193]
    }

    @Override
    public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
        return logRepository.findByStockRecordId(stockRecordId); [cite: 194]
    }

    @Override
    public ConsumptionLog getLog(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ConsumptionLog not found")); [cite: 195]
    }
}