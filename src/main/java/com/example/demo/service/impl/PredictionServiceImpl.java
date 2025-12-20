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
    private final PredictionRuleRepository ruleRepository;
    private final StockRecordRepository stockRecordRepository;
    private final ConsumptionLogRepository logRepository;

    @Override
    public PredictionRule createRule(PredictionRule rule) {
        // Validate averageDaysWindow > 0 [cite: 202]
        if (rule.getAverageDaysWindow() == null || rule.getAverageDaysWindow() <= 0) {
            throw new IllegalArgumentException("averageDaysWindow must be greater than zero"); [cite: 70]
        }
        // Validate minDailyUsage <= maxDailyUsage [cite: 202]
        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
            throw new IllegalArgumentException("minDailyUsage must be less than or equal to maxDailyUsage"); [cite: 70]
        }
        
        rule.setCreatedAt(LocalDateTime.now()); [cite: 203]
        return ruleRepository.save(rule); [cite: 203]
    }

    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {
        // Load stock record [cite: 205]
        StockRecord sr = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found")); [cite: 207]

        // Load logs to compute average daily usage [cite: 205]
        List<ConsumptionLog> logs = logRepository.findByStockRecordId(stockRecordId);
        
        // Logical calculation of predicted restock date [cite: 205, 206]
        double averageDailyUsage = logs.stream()
                .mapToInt(ConsumptionLog::getConsumedQuantity)
                .average().orElse(0.0);

        if (averageDailyUsage <= 0) {
            return LocalDate.now().plusYears(1); // Default if no usage history
        }

        // Calculate days until threshold is reached [cite: 205]
        int currentLevel = sr.getCurrentQuantity();
        int threshold = sr.getReorderThreshold();
        int daysUntilRestock = (int) Math.max(0, (currentLevel - threshold) / averageDailyUsage);

        return LocalDate.now().plusDays(daysUntilRestock); [cite: 206]
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return ruleRepository.findAll(); [cite: 204]
    }
}