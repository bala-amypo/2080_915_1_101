@Override
public StockRecord createStockRecord(String productId, String warehouseId, StockRecord record) {
    return createStockRecord(
            Long.parseLong(productId),
            Long.parseLong(warehouseId),
            record
    );
}

@Override
public StockRecord getStockRecord(String id) {
    return getStockRecord(Long.parseLong(id));
}

@Override
public List<StockRecord> getRecordsBy_product(String productId) {
    return getRecordsBy_product(Long.parseLong(productId));
}

@Override
public List<StockRecord> getRecordsByWarehouse(String warehouseId) {
    return getRecordsByWarehouse(Long.parseLong(warehouseId));
}
