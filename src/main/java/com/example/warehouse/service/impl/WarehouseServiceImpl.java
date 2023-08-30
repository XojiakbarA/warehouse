package com.example.warehouse.service.impl;

import com.example.warehouse.dto.WarehouseDTO;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.WarehouseRepository;
import com.example.warehouse.service.WarehouseService;
import com.example.warehouse.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public Page<Warehouse> findAll(Pageable pageable) {
        return warehouseRepository.findAll(pageable);
    }

    @Override
    public Warehouse findById(Long id) {
        Supplier<ResourceNotFoundException> supplier = () -> {
            String message = Message.createNotFound(Warehouse.class.getSimpleName(), id);
            return new ResourceNotFoundException(message);
        };
        return warehouseRepository.findById(id).orElseThrow(supplier);
    }

    @Override
    public List<Warehouse> findAllByNameContainingIgnoreCase(String name) {
        return warehouseRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @Override
    public void deleteById(Long id) {
        warehouseRepository.deleteById(id);
    }

    @Override
    public void setAttributes(WarehouseDTO dto, Warehouse warehouse) {
        if (dto.getName() != null) {
            warehouse.setName(dto.getName());
        }
        if (dto.getActive() != null) {
            warehouse.setActive(dto.getActive());
        }
    }
}
