package com.example.warehouse.service.impl;

import com.example.warehouse.dto.WarehouseDTO;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.WarehouseRepository;
import com.example.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return warehouseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Warehouse.class.getSimpleName(), id)
        );
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
