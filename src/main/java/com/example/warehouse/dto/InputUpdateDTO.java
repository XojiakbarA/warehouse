package com.example.warehouse.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InputUpdateDTO {
    private Timestamp date;
    private Long warehouseId;
    private Long supplierId;
    private Long currencyId;
    private Integer factureNumber;
}
