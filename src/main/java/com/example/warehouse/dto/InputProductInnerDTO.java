package com.example.warehouse.dto;

import lombok.Data;

import java.util.Date;

@Data
public class InputProductInnerDTO {
    private Long productId;
    private Double amount;
    private Double price;
    private Date expireDate;
}
