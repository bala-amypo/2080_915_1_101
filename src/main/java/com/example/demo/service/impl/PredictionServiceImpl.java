@Service
@RequiredArgsConstructor
public class PredictionService {

    private final StockRecordRepository stockRecordRepository;
    private final ConsumptionLogRepository consumptionLogRepository;
    private final PredictionRuleRepository predictionRuleRepository;

    public LocalDate predictRestockDate(Long stockRecordId) {
        // Load stock record [cite: 207]
        StockRecord stock = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        // Fetch logs to calculate average usage 
        List<ConsumptionLog> logs = consumptionLogRepository.findByStockRecordId(stockRecordId);

        // Logic: Compute Average Daily Usage (Simplified logic based on doc)
        // Note: Real implementation would use 'averageDaysWindow' from a PredictionRule
        double averageDailyUsage = logs.stream()
                .mapToInt(ConsumptionLog::getConsumedQuantity)
                .average()
                .orElse(1.0); // Default to 1 to avoid division by zero

        if (averageDailyUsage == 0) averageDailyUsage = 1.0;

        // Calculate days remaining 
        int currentQty = stock.getCurrentQuantity();
        int threshold = stock.getReorderThreshold();
        
        // Days until we hit the threshold
        long daysRemaining = (long) ((currentQty - threshold) / averageDailyUsage);

        return LocalDate.now().plusDays(daysRemaining); // [cite: 206]
    }

    public PredictionRule createRule(PredictionRule rule) {
        // Validate rules [cite: 202]
        if (rule.getAverageDaysWindow() <= 0) {
            throw new IllegalArgumentException("Average days window must be > 0");
        }
        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
            throw new IllegalArgumentException("Min usage cannot be greater than max usage");
        }
        
        rule.setCreatedAt(LocalDateTime.now()); // [cite: 203]
        return predictionRuleRepository.save(rule);
    }
}