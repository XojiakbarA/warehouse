package com.example.warehouse.dto;

import lombok.Data;

@Data
public class OutputProductInnerDTO {
    private Long inputProductId;
    private Double amount;
    private Double price;
}
