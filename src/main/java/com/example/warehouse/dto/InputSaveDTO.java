package com.example.warehouse.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class InputSaveDTO extends InputUpdateDTO {
    private Set<InputProductInnerDTO> inputProducts;
}
