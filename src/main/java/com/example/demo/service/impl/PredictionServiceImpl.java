package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictionServiceImpl implements PredictionService {
    private final PredictionRuleRepository ruleRepo;
    private final StockRecordRepository stockRepo;
    private final ConsumptionLogRepository logRepo;

    @Override
    public PredictionRule createRule(PredictionRule rule) {
        if (rule.getAverageDaysWindow() <= 0) throw new IllegalArgumentException("window > 0"); // [cite: 202]
        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) throw new IllegalArgumentException("min <= max"); // [cite: 202]
        rule.setCreatedAt(LocalDateTime.now()); // [cite: 203]
        return ruleRepo.save(rule);
    }

    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {
        StockRecord record = stockRepo.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found")); // [cite: 207]
        
        List<ConsumptionLog> logs = logRepo.findByStockRecordId(stockRecordId);
        double avgUsage = logs.stream().mapToInt(ConsumptionLog::getConsumedQuantity).average().orElse(1.0); // [cite: 205]
        
        long daysUntilRestock = (long) ((record.getCurrentQuantity() - record.getReorderThreshold()) / avgUsage);
        return LocalDate.now().plusDays(Math.max(0, daysUntilRestock)); // [cite: 206]
    }

    @Override
    public List<PredictionRule> getAllRules() { return ruleRepo.findAll(); } // [cite: 204]
}