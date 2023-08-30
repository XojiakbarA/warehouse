package com.example.warehouse.service.impl;

import com.example.warehouse.dto.SupplierDTO;
import com.example.warehouse.entity.Supplier;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.SupplierRepository;
import com.example.warehouse.service.SupplierService;
import com.example.warehouse.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Page<Supplier> findAll(Pageable pageable) {
        return supplierRepository.findAll(pageable);
    }

    @Override
    public Supplier findById(Long id) {
        java.util.function.Supplier<ResourceNotFoundException> supplier = () -> {
            String message = Message.createNotFound(Supplier.class.getSimpleName(), id);
            return new ResourceNotFoundException(message);
        };
        return supplierRepository.findById(id).orElseThrow(supplier);
    }

    @Override
    public List<Supplier> findAllByNameContainingIgnoreCase(String name) {
        return supplierRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteById(Long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public void setAttributes(SupplierDTO dto, Supplier supplier) {
        if (dto.getName() != null) {
            supplier.setName(dto.getName());
        }
        if (dto.getActive() != null) {
            supplier.setActive(dto.getActive());
        }
        if (dto.getPhoneNumber() != null) {
            supplier.setPhoneNumber(dto.getPhoneNumber());
        }
    }
}
