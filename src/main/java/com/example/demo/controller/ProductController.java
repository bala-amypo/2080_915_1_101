package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public Product create(@RequestBody Product p) {
        return service.createProduct(p);
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return service.getProduct(id);
    }

    @GetMapping
    public List<Product> list() {
        return service.getAllProducts();
    }
}
