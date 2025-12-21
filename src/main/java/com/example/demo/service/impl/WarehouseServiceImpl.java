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
    private final WarehouseRepository repository;

    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {
        if (warehouse.getLocation() == null || warehouse.getLocation().isEmpty()) {
            throw new IllegalArgumentException("Location must not be empty"); // [cite: 166]
        }
        if (repository.findByWarehouseName(warehouse.getWarehouseName()).isPresent()) {
            throw new IllegalArgumentException("Warehouse name must be unique"); // [cite: 166]
        }
        warehouse.setCreatedAt(LocalDateTime.now()); // [cite: 167]
        return repository.save(warehouse);
    }

    @Override
    public Warehouse getWarehouse(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found")); // [cite: 168]
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return repository.findAll(); // [cite: 169]
    }
}