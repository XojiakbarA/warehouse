package com.example.warehouse.service;

import com.example.warehouse.dto.InputProductInnerDTO;
import com.example.warehouse.entity.Input;
import com.example.warehouse.entity.InputProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InputProductService {
    Page<InputProduct> findAll(Pageable pageable);
    Page<InputProduct> findAllByInputId(Long inputId, Pageable pageable);
    InputProduct findById(Long id);
    InputProduct save(InputProduct inputProduct);
    void deleteById(Long id);
    void deleteAllByInputId(Long inputId);
    void setAttributes(InputProductInnerDTO dto, InputProduct inputProduct, Input input);
}
