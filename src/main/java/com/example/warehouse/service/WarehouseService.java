package com.example.warehouse.service;

import com.example.warehouse.dto.WarehouseDTO;
import com.example.warehouse.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface WarehouseService {
    Page<Warehouse> findAll(Pageable pageable);

    Optional<Warehouse> findById(Long id);

    Warehouse save(Warehouse warehouse);

    void setAttributes(WarehouseDTO dto, Warehouse warehouse);

    boolean existsById(Long id);

    void deleteById(Long id);
}
