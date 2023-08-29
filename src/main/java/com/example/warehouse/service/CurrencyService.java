package com.example.warehouse.service;

import com.example.warehouse.dto.CurrencyDTO;
import com.example.warehouse.entity.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface CurrencyService {
    Page<Currency> findAll(Pageable pageable);

    List<Currency> findAll();

    Currency findById(Long id);

    Set<java.util.Currency> findAvailableCurrency(String name);

    Currency save(Currency currency);

    void deleteById(Long id);

    void setAttributes(CurrencyDTO dto, Currency currency);
}
