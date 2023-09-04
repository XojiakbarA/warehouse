package com.example.warehouse.controller;

import com.example.warehouse.entity.InputProduct;
import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private String message;
    private InputProduct inputProduct;
}
