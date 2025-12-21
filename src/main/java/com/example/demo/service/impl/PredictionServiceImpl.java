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

@Service [cite: 149]
@RequiredArgsConstructor [cite: 150]
public class PredictionServiceImpl implements PredictionService {

    private final PredictionRuleRepository ruleRepo;
    private final StockRecordRepository stockRepo;
    private final ConsumptionLogRepository logRepo;

    @Override
    public PredictionRule createRule(PredictionRule rule) {
        // Validation: averageDaysWindow must be greater than zero [cite: 70, 202]
        if (rule.getAverageDaysWindow() <= 0) {
            throw new IllegalArgumentException("averageDaysWindow must be greater than zero"); [cite: 70, 202]
        }
        // Validation: minDailyUsage must be less than or equal to maxDailyUsage [cite: 70, 202]
        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
            throw new IllegalArgumentException("minDailyUsage must be less than or equal to maxDailyUsage"); [cite: 70, 202]
        }
        // Check for unique rule name [cite: 71, 202]
        if (ruleRepo.findByRuleName(rule.getRuleName()).isPresent()) {
            throw new IllegalArgumentException("Rule name must be unique"); [cite: 71, 202]
        }

        rule.setCreatedAt(LocalDateTime.now()); [cite: 69, 203]
        return ruleRepo.save(rule); [cite: 203]
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return ruleRepo.findAll(); [cite: 204]
    }

    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {
        // Load stock record or throw ResourceNotFoundException [cite: 198, 207]
        StockRecord record = stockRepo.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found")); [cite: 181, 207]

        // Load consumption logs for the record 
        List<ConsumptionLog> logs = logRepo.findByStockRecordId(stockRecordId); [cite: 121]

        // Compute average daily usage over the window defined in rules 
        // Using an explicit lambda to avoid method reference errors
        double avgUsage = logs.stream()
                .mapToInt(log -> log.getConsumedQuantity()) [cite: 56]
                .average()
                .orElse(1.0); // Default to 1 to prevent division by zero

        // Calculate days remaining until quantity drops to reorder threshold 
        long daysRemaining = (long) ((record.getCurrentQuantity() - record.getReorderThreshold()) / avgUsage); [cite: 46, 48, 205]

        // Return the predicted restock date [cite: 206]
        return LocalDate.now().plusDays(Math.max(0, daysRemaining)); [cite: 206]
    }
}