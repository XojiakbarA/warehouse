package com.example.warehouse.service.impl;

import com.example.warehouse.dto.OutputUpdateDTO;
import com.example.warehouse.entity.Client;
import com.example.warehouse.entity.Currency;
import com.example.warehouse.entity.Output;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.OutputRepository;
import com.example.warehouse.service.ClientService;
import com.example.warehouse.service.CurrencyService;
import com.example.warehouse.service.OutputService;
import com.example.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OutputServiceImpl implements OutputService {

    @Autowired
    private OutputRepository outputRepository;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CurrencyService currencyService;

    @Override
    public Page<Output> findAll(Pageable pageable) {
        return outputRepository.findAll(pageable);
    }

    @Override
    public Output findById(Long id) {
        return outputRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Output.class.getSimpleName(), id)
        );
    }

    @Override
    public Output save(Output output) {
        return outputRepository.save(output);
    }

    @Override
    public void deleteById(Long id) {
        outputRepository.deleteById(id);
    }

    @Override
    public void setAttributes(OutputUpdateDTO dto, Output output) {
        if (dto.getWarehouseId() != null) {
            Warehouse warehouse = warehouseService.findById(dto.getWarehouseId());
            output.setWarehouse(warehouse);
        }
        if (dto.getClientId() != null) {
            Client client = clientService.findById(dto.getClientId());
            output.setClient(client);
        }
        if (dto.getCurrencyId() != null) {
            Currency currency = currencyService.findById(dto.getCurrencyId());
            output.setCurrency(currency);
        }
        if (dto.getDate() != null) {
            output.setDate(dto.getDate());
        }
        if (dto.getFactureNumber() != null) {
            output.setFactureNumber(dto.getFactureNumber());
        }
        output.setCode(UUID.randomUUID().toString());
    }
}
