package com.example.demo.controller;

import com.example.demo.model.Warehouse;
import com.example.demo.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        return ResponseEntity.ok(warehouseService.createWarehouse(warehouse));
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        return ResponseEntity.ok(warehouseService.getAllWarehouses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouse(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.getWarehouse(id));
    }
}