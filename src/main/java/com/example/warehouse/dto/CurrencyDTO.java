package com.example.warehouse.dto;

import lombok.Data;

import java.util.Currency;

@Data
public class CurrencyDTO {
    private Currency currencyCode;
    private Boolean active;
}
