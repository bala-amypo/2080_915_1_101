package com.example.demo.controller;

import com.example.demo.model.Warehouse;
import com.example.demo.service.WarehouseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@Tag(name = "Warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.createWarehouse(warehouse);
    }

    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public Warehouse getWarehouse(@PathVariable Long id) {
        return warehouseService.getWarehouse(id);
    }
}
