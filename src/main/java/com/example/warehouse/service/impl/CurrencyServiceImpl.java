package com.example.warehouse.service.impl;

import com.example.warehouse.dto.CurrencyDTO;
import com.example.warehouse.entity.Currency;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.CurrencyRepository;
import com.example.warehouse.service.CurrencyService;
import com.example.warehouse.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Page<Currency> findAll(Pageable pageable) {
        return currencyRepository.findAll(pageable);
    }

    @Override
    public Currency findById(Long id) {
        Supplier<ResourceNotFoundException> supplier = () -> {
            String message = Message.createNotFound(Currency.class.getSimpleName(), id);
            return new ResourceNotFoundException(message);
        };
        return currencyRepository.findById(id).orElseThrow(supplier);
    }

    @Override
    public Currency save(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public void deleteById(Long id) {
        currencyRepository.deleteById(id);
    }

    @Override
    public void setAttributes(CurrencyDTO dto, Currency currency) {
        if (dto.getName() != null) {
            currency.setName(dto.getName());
        }
        if (dto.getActive() != null) {
            currency.setActive(dto.getActive());
        }
    }
}