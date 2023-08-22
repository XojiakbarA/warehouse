package com.example.warehouse.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private String name;
    private Boolean active;
    private Long parentCategoryId;
}
