@Entity
public class StockRecord {
    @Id @GeneratedValue
    private Long id;
    private int quantity;
}
