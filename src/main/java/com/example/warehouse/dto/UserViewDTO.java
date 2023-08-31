package com.example.warehouse.dto;

import com.example.warehouse.entity.Warehouse;
import lombok.Data;

import java.util.Set;

@Data
public class UserViewDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String code;
    private Boolean active;
    private Set<Warehouse> warehouses;
}
