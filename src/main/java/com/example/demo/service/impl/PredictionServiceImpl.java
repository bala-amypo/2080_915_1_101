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
    private final StockRecordRepository stockRecordRepository;
    private final ConsumptionLogRepository consumptionLogRepository;
    private final PredictionRuleRepository predictionRuleRepository;

    @Override
    public PredictionRule createRule(PredictionRule rule) {
        if (rule.getAverageDaysWindow() <= 0) {
            throw new IllegalArgumentException("Window must be > 0");
        }
        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
            throw new IllegalArgumentException("Min cannot be > Max");
        }
        if (predictionRuleRepository.findByRuleName(rule.getRuleName()).isPresent()) {
            throw new IllegalArgumentException("Rule name must be unique");
        }

        rule.setCreatedAt(LocalDateTime.now());
        return predictionRuleRepository.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return predictionRuleRepository.findAll();
    }

    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {
        StockRecord record = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        int windowDays = 30;
        List<PredictionRule> rules = predictionRuleRepository.findAll();
        if (!rules.isEmpty()) {
            windowDays = rules.get(0).getAverageDaysWindow();
        }

        LocalDate cutoffDate = LocalDate.now().minusDays(windowDays);
        List<ConsumptionLog> logs = consumptionLogRepository.findByStockRecordId(stockRecordId);

        double totalConsumed = logs.stream()
                .filter(l -> !l.getConsumedDate().isBefore(cutoffDate))
                .mapToInt(ConsumptionLog::getConsumedQuantity)
                .sum();

        double avgDailyUsage = totalConsumed / windowDays;

        if (avgDailyUsage == 0) {
            return LocalDate.now().plusYears(1);
        }

        int currentQty = record.getCurrentQuantity();
        int threshold = record.getReorderThreshold();

        double daysRemaining = (currentQty - threshold) / avgDailyUsage;

        if (daysRemaining < 0) {
            return LocalDate.now();
        }

        return LocalDate.now().plusDays((long) daysRemaining);
    }
}