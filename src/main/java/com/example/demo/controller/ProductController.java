package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")   // ✅ IMPORTANT
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ✅ t7_createProduct
    @PostMapping
    public ResponseEntity<Product> createProduct(
            @Valid @RequestBody Product product) {

        return ResponseEntity.ok(productService.save(product));
    }

    // ✅ t8_getProduct
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {

        return ResponseEntity.ok(productService.getById(id));
    }

    // ✅ t9_listProducts
    @GetMapping
    public ResponseEntity<List<Product>> listProducts() {

        return ResponseEntity.ok(productService.getAll());
    }
}
