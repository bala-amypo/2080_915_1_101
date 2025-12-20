package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.WarehouseRepository;
import com.example.demo.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {
        // Validation: Location must not be empty [cite: 38, 166]
        if (warehouse.getLocation() == null || warehouse.getLocation().isBlank()) {
            throw new IllegalArgumentException("Location must not be empty");
        }

        // Validation: Warehouse name must be unique [cite: 38, 166]
        if (warehouseRepository.findByWarehouseName(warehouse.getWarehouseName()).isPresent()) {
            throw new IllegalArgumentException("Warehouse name must be unique");
        }

        // Set createdAt if missing 
        if (warehouse.getCreatedAt() == null) {
            warehouse.setCreatedAt(LocalDateTime.now());
        }

        return warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse getWarehouse(Long id) {
        // Throws ResourceNotFoundException if no record exists [cite: 168]
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        // Returns all warehouses [cite: 169]
        return warehouseRepository.findAll();
    }
}