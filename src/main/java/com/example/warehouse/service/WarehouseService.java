package com.example.warehouse.service;

import com.example.warehouse.dto.WarehouseDTO;
import com.example.warehouse.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarehouseService {
    Page<Warehouse> findAll(Pageable pageable);

    Warehouse findById(Long id);

    Warehouse save(Warehouse warehouse);

    void deleteById(Long id);

    void setAttributes(WarehouseDTO dto, Warehouse warehouse);
}
