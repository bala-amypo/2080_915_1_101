package com.example.demo.service;

import com.example.demo.model.Product;
import java.util.List;

public interface ProductService {

    Product create(Product product);

    Product getProduct(Long id);

    List<Product> getAllProducts();

    // ✅ ADD THIS (to fix compilation error)
    default Product save(Product product) {
        return create(product);
    }
}
