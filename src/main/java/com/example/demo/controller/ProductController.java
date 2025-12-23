package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products") // ✅ THIS IS THE KEY FIX
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // t7_createProduct
    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.save(product);
    }

    // t8_getProduct
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    // t9_listProducts
    @GetMapping
    public List<Product> listProducts() {
        return productService.getAllProducts();
    }
}
