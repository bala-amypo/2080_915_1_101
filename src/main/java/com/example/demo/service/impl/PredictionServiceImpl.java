package com.example.demo.service.impl;

import com.example.demo.dto.PredictionResponse;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.PredictionRule;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.PredictionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final StockRecordRepository stockRecordRepository;
    private final ConsumptionLogRepository consumptionLogRepository;
    private final PredictionRuleRepository predictionRuleRepository;

    public PredictionServiceImpl(
            StockRecordRepository stockRecordRepository,
            ConsumptionLogRepository consumptionLogRepository,
            PredictionRuleRepository predictionRuleRepository
    ) {
        this.stockRecordRepository = stockRecordRepository;
        this.consumptionLogRepository = consumptionLogRepository;
        this.predictionRuleRepository = predictionRuleRepository;
    }

    @Override
    public PredictionResponse predict(Long stockRecordId) {

        StockRecord stock = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        List<ConsumptionLog> logs =
                consumptionLogRepository.findByStockRecord_Id(stockRecordId);

        int totalConsumed = logs.stream()
                .mapToInt(ConsumptionLog::getQuantityUsed)
                .sum();

        boolean reorderRequired =
                stock.getCurrentQuantity() <= stock.getReorderThreshold();

        return new PredictionResponse(
                stock.getId(),
                totalConsumed,
                reorderRequired
        );
    }

    @Override
    public PredictionRule createRule(PredictionRule rule) {
        return predictionRuleRepository.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return predictionRuleRepository.findAll();
    }
}
