package com.example.warehouse.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OutputProductDTO extends OutputProductInnerDTO {
    private Long outputId;
}
