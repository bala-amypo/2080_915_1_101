package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ✅ used by POST /products
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    // ✅ used by GET /products/{id}
    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));
    }

    // ✅ used by GET /products
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
