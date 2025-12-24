package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    /**
     * Creates a new warehouse after validating business rules.
     * Rules: warehouseName must be unique, location must not be empty.
     */
    public Warehouse createWarehouse(Warehouse warehouse) {
        // Validation: warehouseName must not be empty [cite: 38]
        if (warehouse.getWarehouseName() == null || warehouse.getWarehouseName().trim().isEmpty()) {
            throw new IllegalArgumentException("Warehouse name cannot be empty");
        }

        // Validation: location must not be empty 
        if (warehouse.getLocation() == null || warehouse.getLocation().trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty");
        }

        // Validation: warehouseName must be unique 
        // Using Example matcher to check uniqueness without adding a custom method to the Repository interface
        Warehouse probe = Warehouse.builder().warehouseName(warehouse.getWarehouseName()).build();
        if (warehouseRepository.exists(Example.of(probe))) {
            throw new IllegalArgumentException("Warehouse with this name already exists");
        }

        // Set createdAt if missing [cite: 167]
        if (warehouse.getCreatedAt() == null) {
            warehouse.setCreatedAt(LocalDateTime.now());
        }

        return warehouseRepository.save(warehouse);
    }

    /**
     * Retrieves a warehouse by ID.
     * Throws ResourceNotFoundException if not found.
     */
    public Warehouse getWarehouse(Long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
    }

    /**
     * Returns all warehouses[cite: 169].
     */
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }
}