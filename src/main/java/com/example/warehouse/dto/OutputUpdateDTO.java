package com.example.warehouse.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutputUpdateDTO {
    private Timestamp date;
    private Long warehouseId;
    private Long clientId;
    private Long currencyId;
    private Integer factureNumber;
}
