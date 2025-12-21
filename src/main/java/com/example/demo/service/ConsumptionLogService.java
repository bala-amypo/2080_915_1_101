package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.StockRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumptionLogService {
    private final ConsumptionLogRepository consumptionLogRepository;
    private final StockRecordRepository stockRecordRepository;

    public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log) {
        StockRecord stockRecord = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        if (log.getConsumedDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("consumedDate cannot be future"); // Exact keyword required
        }
        if (log.getConsumedQuantity() <= 0) {
            throw new IllegalArgumentException("Consumed quantity must be > 0");
        }

        log.setStockRecord(stockRecord);
        // Reduce stock quantity? Requirement doesn't explicitly state to update stockRecord quantity here,
        // but typically inventory systems do. However, based on "consumption history" and strict "logConsumption" wording,
        // we will stick to saving the log. If update needed, uncomment below:
        // stockRecord.setCurrentQuantity(stockRecord.getCurrentQuantity() - log.getConsumedQuantity());
        // stockRecordRepository.save(stockRecord);

        return consumptionLogRepository.save(log);
    }

    public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
        return consumptionLogRepository.findByStockRecordId(stockRecordId);
    }

    public ConsumptionLog getLog(Long id) {
        return consumptionLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ConsumptionLog not found"));
    }
}