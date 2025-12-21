package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.PredictionRule;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.repository.StockRecordRepository;
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
        // Rule: averageDaysWindow must be greater than zero [cite: 70, 202]
        if (rule.getAverageDaysWindow() <= 0) {
            throw new IllegalArgumentException("averageDaysWindow must be greater than zero");
        }
        
        // Rule: minDailyUsage must be less than or equal to maxDailyUsage [cite: 70, 202]
        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
            throw new IllegalArgumentException("minDailyUsage must be less than or equal to maxDailyUsage");
        }
        
        // Rule: ruleName must be unique [cite: 71, 202]
        if (ruleRepo.findByRuleName(rule.getRuleName()).isPresent()) {
            throw new IllegalArgumentException("Rule name must be unique");
        }

        // Set creation timestamp [cite: 69, 203]
        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepo.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        // Returns all prediction rules [cite: 204]
        return ruleRepo.findAll();
    }

    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {
        // Load stock record or throw ResourceNotFoundException 
        StockRecord record = stockRepo.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        // Load consumption logs for this record [cite: 205]
        List<ConsumptionLog> logs = logRepo.findByStockRecordId(stockRecordId);

        // Calculate average daily usage [cite: 205]
        double avgUsage = logs.stream()
                .mapToInt(log -> log.getConsumedQuantity())
                .average()
                .orElse(1.0); // Avoid division by zero

        // Calculate days remaining until quantity drops to the reorder threshold [cite: 205]
        long daysRemaining = (long) ((record.getCurrentQuantity() - record.getReorderThreshold()) / avgUsage);

        // Return the predicted restock date [cite: 206]
        return LocalDate.now().plusDays(Math.max(0, daysRemaining));
    }
}