@Service
@RequiredArgsConstructor
public class ConsumptionLogServiceImpl implements ConsumptionLogService {
    private final ConsumptionLogRepository repository;
    private final StockRecordRepository stockRepo;

    @Override
    public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log) {
        StockRecord sr = stockRepo.findById(stockRecordId)
            .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found")); [cite: 190]
        
        if (log.getConsumedQuantity() <= 0) throw new IllegalArgumentException("Invalid quantity");
        if (log.getConsumedDate().isAfter(java.time.LocalDate.now())) {
            throw new IllegalArgumentException("consumedDate cannot be future"); [cite: 192]
        }
        
        log.setStockRecord(sr);
        return repository.save(log);
    }

    @Override
    public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
        return repository.findByStockRecordId(stockRecordId);
    }
}