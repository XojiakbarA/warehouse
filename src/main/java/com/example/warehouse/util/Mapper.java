package com.example.warehouse.util;

import com.example.warehouse.dto.CurrencyAvailableDTO;
import com.example.warehouse.dto.CurrencyViewDTO;
import com.example.warehouse.entity.Currency;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public static CurrencyViewDTO currencyEntityToCurrencyViewDTO(Currency currency) {
        CurrencyViewDTO dto = new CurrencyViewDTO();
        dto.setId(currency.getId());
        dto.setCurrencyCode(currency.getCurrencyCode());
        dto.setCurrencyName(currency.getCurrencyCode().getDisplayName());
        dto.setActive(currency.getActive());
        return dto;
    }
    public static CurrencyAvailableDTO currencyUtilToCurrencyAvailableDTO(java.util.Currency currency) {
        CurrencyAvailableDTO dto = new CurrencyAvailableDTO();
        dto.setCurrencyCode(currency.getCurrencyCode());
        dto.setCurrencyName(currency.getDisplayName());
        return dto;
    }
}
