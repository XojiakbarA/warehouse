package com.example.warehouse.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String code;
    private String password;
    private Boolean active;
    private Set<Long> warehouseIds;
}
