package com.example.warehouse.dto;

import lombok.Data;

import java.util.Date;

@Data
public class InputProductNearToExpireDTO {
    private Long id;
    private Double amount;
    private Double price;
    private Date expireDate;
}
