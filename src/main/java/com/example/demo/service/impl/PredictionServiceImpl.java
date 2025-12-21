// Inside PredictionServiceImpl.java
@Override
public LocalDate predictRestockDate(Long stockRecordId) {
    StockRecord record = stockRepo.findById(stockRecordId)
            .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));
    
    List<ConsumptionLog> logs = logRepo.findByStockRecordId(stockRecordId);
    
    // Fix: Use explicit lambda log -> log.getConsumedQuantity()
    double avgUsage = logs.stream()
            .mapToInt(log -> log.getConsumedQuantity()) 
            .average()
            .orElse(1.0);
    
    long daysUntilRestock = (long) ((record.getCurrentQuantity() - record.getReorderThreshold()) / avgUsage);
    return LocalDate.now().plusDays(Math.max(0, daysUntilRestock));
}