public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
public interface ProductRepository extends JpaRepository<Product, Long> {}
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {}
public interface StockRecordRepository extends JpaRepository<StockRecord, Long> {}
public interface PredictionRuleRepository extends JpaRepository<PredictionRule, Long> {}
public interface ConsumptionLogRepository extends JpaRepository<ConsumptionLog, Long> {}
