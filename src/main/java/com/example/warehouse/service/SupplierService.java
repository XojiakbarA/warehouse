package com.example.warehouse.service;

import com.example.warehouse.dto.SupplierDTO;
import com.example.warehouse.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {
    Page<Supplier> findAll(Pageable pageable);

    Supplier findById(Long id);

    Supplier save(Supplier supplier);

    void deleteById(Long id);

    void setAttributes(SupplierDTO dto, Supplier supplier);
}
