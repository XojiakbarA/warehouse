package com.example.warehouse.service;

import com.example.warehouse.dto.CurrencyDTO;
import com.example.warehouse.entity.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CurrencyService {
    Page<Currency> findAll(Pageable pageable);

    Currency findById(Long id);

    Currency save(Currency currency);

    void deleteById(Long id);

    void setAttributes(CurrencyDTO dto, Currency currency);
}
