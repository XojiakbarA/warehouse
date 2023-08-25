package com.example.warehouse.dto;

import lombok.Data;

@Data
public class OutputProductInnerDTO {
    private Long productId;
    private Double amount;
    private Double price;
}
