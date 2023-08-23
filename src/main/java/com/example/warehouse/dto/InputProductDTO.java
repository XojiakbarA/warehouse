package com.example.warehouse.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InputProductDTO extends InputProductInnerDTO {
    private Long inputId;
}
