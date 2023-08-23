package com.example.warehouse.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDTO {
    private String name;
    private Boolean active;
    private Long categoryId;
    private MultipartFile photo;
    private Long measurementId;
}
