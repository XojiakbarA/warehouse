package com.example.warehouse.service.impl;

import com.example.warehouse.dto.InputUpdateDTO;
import com.example.warehouse.entity.*;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.InputRepository;
import com.example.warehouse.service.*;
import com.example.warehouse.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InputServiceImpl implements InputService {

    @Autowired
    private InputRepository inputRepository;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private InputProductService inputProductService;

    @Override
    public Page<Input> findAll(Pageable pageable) {
        return inputRepository.findAll(pageable);
    }

    @Override
    public Input findById(Long id) {
        java.util.function.Supplier<ResourceNotFoundException> supplier = () -> {
            String message = Message.createNotFound(Input.class.getSimpleName(), id);
            return new ResourceNotFoundException(message);
        };
        return inputRepository.findById(id).orElseThrow(supplier);
    }

    @Override
    public Input save(Input input) {
        return inputRepository.save(input);
    }

    @Override
    public void deleteById(Long id) {
        inputProductService.deleteAllByInputId(id);
        inputRepository.deleteById(id);
    }

    @Override
    public void setAttributes(InputUpdateDTO dto, Input input) {
        if (dto.getWarehouseId() != null) {
            Warehouse warehouse = warehouseService.findById(dto.getWarehouseId());
            input.setWarehouse(warehouse);
        }
        if (dto.getSupplierId() != null) {
            Supplier supplier = supplierService.findById(dto.getSupplierId());
            input.setSupplier(supplier);
        }
        if (dto.getCurrencyId() != null) {
            Currency currency = currencyService.findById(dto.getCurrencyId());
            input.setCurrency(currency);
        }
        if (dto.getDate() != null) {
            input.setDate(dto.getDate());
        }
        if (dto.getFactureNumber() != null) {
            input.setFactureNumber(dto.getFactureNumber());
        }
        input.setCode(UUID.randomUUID().toString());
    }
}
