package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.PredictionRule;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.repository.StockRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictionService {
    private final StockRecordRepository stockRecordRepository;
    private final ConsumptionLogRepository consumptionLogRepository;
    private final PredictionRuleRepository predictionRuleRepository;

    public PredictionRule createRule(PredictionRule rule) {
        if (rule.getAverageDaysWindow() <= 0) throw new IllegalArgumentException("Window must be > 0");
        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) throw new IllegalArgumentException("Min cannot be > Max");
        
        rule.setCreatedAt(LocalDateTime.now());
        return predictionRuleRepository.save(rule);
    }

    public List<PredictionRule> getAllRules() {
        return predictionRuleRepository.findAll();
    }

    public LocalDate predictRestockDate(Long stockRecordId) {
        StockRecord record = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        // Use default rule logic or fetch specific rule. Assuming simple logic as per description.
        // Retrieve all logs for this record
        List<ConsumptionLog> logs = consumptionLogRepository.findByStockRecordId(stockRecordId);
        
        // Let's assume a default window of 30 days if no rule is specifically applied, 
        // or fetch the first rule available.
        int windowDays = 30;
        List<PredictionRule> rules = predictionRuleRepository.findAll();
        if(!rules.isEmpty()) {
            windowDays = rules.get(0).getAverageDaysWindow();
        }

        LocalDate cutoffDate = LocalDate.now().minusDays(windowDays);
        
        double totalConsumed = logs.stream()
                .filter(l -> !l.getConsumedDate().isBefore(cutoffDate))
                .mapToInt(ConsumptionLog::getConsumedQuantity)
                .sum();
                
        // Calculate average daily usage
        double avgDailyUsage = totalConsumed / windowDays;
        
        if (avgDailyUsage == 0) return LocalDate.now().plusYears(1); // No usage, safe date

        int currentQty = record.getCurrentQuantity();
        int threshold = record.getReorderThreshold();
        
        // Days remaining until we hit threshold
        double daysRemaining = (currentQty - threshold) / avgDailyUsage;
        
        if (daysRemaining < 0) return LocalDate.now(); // Already below threshold

        return LocalDate.now().plusDays((long) daysRemaining);
    }
}