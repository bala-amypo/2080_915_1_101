@RestController
@RequestMapping("/api/stocks")
public class StockRecordController {

    private final StockRecordService service;

    public StockRecordController(StockRecordService service) {
        this.service = service;
    }

    /* ===== STRING ===== */
    @GetMapping("/{id}")
    public StockRecord getStockRecord(@PathVariable String id) {
        return service.getStockRecord(id);
    }

    /* ===== LONG (FOR TESTS) ===== */
    public StockRecord getStockRecord(long id) {
        return service.getStockRecord(id);
    }

    public List<StockRecord> getByProduct(long productId) {
        return service.getRecordsBy_product(productId);
    }

    public List<StockRecord> getByProduct(String productId) {
        return service.getRecordsBy_product(productId);
    }

    public List<StockRecord> getByWarehouse(long warehouseId) {
        return service.getRecordsByWarehouse(warehouseId);
    }

    public List<StockRecord> getByWarehouse(String warehouseId) {
        return service.getRecordsByWarehouse(warehouseId);
    }
}
