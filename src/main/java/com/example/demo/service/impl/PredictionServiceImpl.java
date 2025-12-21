@Service
@RequiredArgsConstructor
public class PredictionServiceImpl implements PredictionService {
    private final PredictionRuleRepository ruleRepo;
    private final StockRecordRepository stockRepo;
    private final ConsumptionLogRepository logRepo;

    @Override
    public java.time.LocalDate predictRestockDate(Long stockRecordId) {
        StockRecord sr = stockRepo.findById(stockRecordId)
            .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found")); [cite: 207]
        
        List<ConsumptionLog> logs = logRepo.findByStockRecordId(stockRecordId);
        // Business logic: Simplified daily average calculation [cite: 205]
        double avgUsage = logs.stream().mapToInt(ConsumptionLog::getConsumedQuantity).average().orElse(1.0);
        long daysRemaining = (long) ((sr.getCurrentQuantity() - sr.getReorderThreshold()) / avgUsage);
        
        return java.time.LocalDate.now().plusDays(Math.max(0, daysRemaining)); [cite: 206]
    }

    @Override
    public PredictionRule createRule(PredictionRule rule) {
        if (rule.getAverageDaysWindow() <= 0) throw new IllegalArgumentException("Invalid window");
        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
            throw new IllegalArgumentException("min cannot exceed max"); [cite: 70, 202]
        }
        rule.setCreatedAt(java.time.LocalDateTime.now());
        return ruleRepo.save(rule);
    }
}