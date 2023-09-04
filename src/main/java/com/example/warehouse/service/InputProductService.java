package com.example.warehouse.service;

import com.example.warehouse.dto.InputProductInnerDTO;
import com.example.warehouse.dto.dashboard.TotalAmountDTO;
import com.example.warehouse.dto.dashboard.TotalCostDTO;
import com.example.warehouse.entity.Input;
import com.example.warehouse.entity.InputProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InputProductService extends ActiveCheckable {
    Page<InputProduct> findAll(Pageable pageable);
    Page<InputProduct> findAllByInputId(Long inputId, Pageable pageable);
    List<InputProduct> findAllByProductNameContainingIgnoreCase(String productName);
    List<InputProduct> findAllNearToExpire();
    Boolean isNearToExpire(Long id);
    Long countNearToExpire();
    InputProduct findById(Long id);
    InputProduct save(InputProduct inputProduct);
    void deleteById(Long id);
    void setAttributes(InputProductInnerDTO dto, InputProduct inputProduct, Input input);

    void subtractRemainingById(Long id, Double amount);

    List<TotalCostDTO> findDailyInputTotalCost();
    List<TotalAmountDTO> findDailyInputTotalAmount();
}
