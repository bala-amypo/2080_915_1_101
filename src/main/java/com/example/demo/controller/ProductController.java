package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/products") // [cite: 224]
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping public Product create(@RequestBody Product p) { return service.createProduct(p); } // [cite: 228]
    @GetMapping public List<Product> getAll() { return service.getAllProducts(); } // [cite: 230]
    @GetMapping("/{id}") public Product get(@PathVariable Long id) { return service.getProduct(id); } // [cite: 233]
}