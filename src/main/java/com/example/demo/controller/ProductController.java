package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    /* ===== REST ===== */
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        return service.getProduct(id);
    }

    /* ===== TEST SUPPORT ===== */
    public Product getProduct(long id) {
        return service.getProduct(id);
    }

    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }
}
