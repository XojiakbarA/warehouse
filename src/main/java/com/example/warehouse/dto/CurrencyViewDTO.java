package com.example.warehouse.dto;

import lombok.Data;

@Data
public class CurrencyViewDTO {
    private Long id;
    private java.util.Currency currencyCode;
    private String currencyName;
    private Boolean active;
}
