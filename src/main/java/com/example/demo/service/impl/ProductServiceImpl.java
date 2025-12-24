@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        // Validation: Product name must not be empty [cite: 30, 157]
        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        // Validation: SKU must be unique [cite: 30, 110]
        if (productRepository.findBySku(product.getSku()).isPresent()) {
            throw new IllegalArgumentException("Product with this SKU already exists");
        }

        product.setCreatedAt(LocalDateTime.now()); // [cite: 157]
        return productRepository.save(product);
    }

    public Product getProduct(Long id) {
        // Retrieve or throw ResourceNotFoundException [cite: 158]
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll(); // [cite: 159]
    }
}