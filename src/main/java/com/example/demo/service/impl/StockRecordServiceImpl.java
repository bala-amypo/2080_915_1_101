@Service
@RequiredArgsConstructor
public class StockRecordServiceImpl implements StockRecordService {

    private final StockRecordRepository stockRecordRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord record) {
        // Validate existence of Product and Warehouse [cite: 177, 178]
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));

        // Critical Rule: Check for duplicates [cite: 50, 179]
        Optional<StockRecord> existing = stockRecordRepository.findByProductAndWarehouse(product, warehouse);
        if (existing.isPresent()) {
            // Exact phrase required by Source 50
            throw new IllegalArgumentException("StockRecord already exists");
        }

        // Validate quantities [cite: 48, 180]
        if (record.getCurrentQuantity() < 0 || record.getReorderThreshold() <= 0) {
            throw new IllegalArgumentException("Invalid quantity or threshold");
        }

        record.setProduct(product);
        record.setWarehouse(warehouse);
        record.setLastUpdated(LocalDateTime.now()); // [cite: 180]
        
        return stockRecordRepository.save(record);
    }

    public StockRecord getStockRecord(Long id) {
        return stockRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found")); // [cite: 181]
    }

    public List<StockRecord> getRecordsBy_product(Long productId) {
        return stockRecordRepository.findByProductId(productId); // [cite: 182]
    }
}