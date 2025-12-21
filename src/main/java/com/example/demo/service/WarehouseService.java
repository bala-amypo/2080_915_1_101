package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public Warehouse createWarehouse(Warehouse warehouse) {
        if (warehouse.getLocation() == null || warehouse.getLocation().isEmpty()) {
            throw new IllegalArgumentException("Location must not be empty");
        }
        if (warehouseRepository.findByWarehouseName(warehouse.getWarehouseName()).isPresent()) {
            throw new IllegalArgumentException("Warehouse name already exists");
        }
        if (warehouse.getCreatedAt() == null) {
            warehouse.setCreatedAt(LocalDateTime.now());
        }
        return warehouseRepository.save(warehouse);
    }

    public Warehouse getWarehouse(Long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }
}