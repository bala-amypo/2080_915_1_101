@Service
@RequiredArgsConstructor
public class ConsumptionLogService {

    private final ConsumptionLogRepository consumptionLogRepository;
    private final StockRecordRepository stockRecordRepository;

    public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log) {
        // Validate StockRecord exists [cite: 190]
        StockRecord stockRecord = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        // Critical Rule: Date cannot be future [cite: 58, 59, 192]
        if (log.getConsumedDate().isAfter(LocalDate.now())) {
            // Exact phrase required by Source 59
            throw new IllegalArgumentException("consumedDate cannot be future");
        }

        // Validate consumed quantity [cite: 58, 191]
        if (log.getConsumedQuantity() <= 0) {
            throw new IllegalArgumentException("Consumed quantity must be greater than zero");
        }

        log.setStockRecord(stockRecord);
        return consumptionLogRepository.save(log);
    }

    public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
        return consumptionLogRepository.findByStockRecordId(stockRecordId); // [cite: 194]
    }
}