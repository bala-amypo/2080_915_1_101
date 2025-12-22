package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.PredictionRule;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.PredictionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final StockRecordRepository stockRecordRepository;
    private final ConsumptionLogRepository consumptionLogRepository;
    private final PredictionRuleRepository ruleRepository;

    public PredictionServiceImpl(
            StockRecordRepository stockRecordRepository,
            ConsumptionLogRepository consumptionLogRepository,
            PredictionRuleRepository ruleRepository) {
        this.stockRecordRepository = stockRecordRepository;
        this.consumptionLogRepository = consumptionLogRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public LocalDate predictRestockDate(String stockRecordId) {

        Long id = Long.parseLong(stockRecordId);

        StockRecord record = stockRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        List<ConsumptionLog> logs =
                consumptionLogRepository.findByStockRecordIdOrderByConsumedDateDesc(id);

        if (logs.isEmpty()) {
            return LocalDate.now().plusDays(30);
        }

        int avgDailyConsumption =
                logs.stream().mapToInt(ConsumptionLog::getConsumedQuantity).sum() / logs.size();

        if (avgDailyConsumption == 0) {
            return LocalDate.now().plusDays(30);
        }

        int daysLeft = record.getQuantity() / avgDailyConsumption;

        return LocalDate.now().plusDays(daysLeft);
    }

    @Override
    public PredictionRule createRule(PredictionRule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return ruleRepository.findAll();
    }
}
