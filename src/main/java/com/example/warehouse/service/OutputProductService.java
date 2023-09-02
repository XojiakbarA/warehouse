package com.example.warehouse.service;

import com.example.warehouse.dto.OutputProductInnerDTO;
import com.example.warehouse.dto.dashboard.MostOutputProductsDTO;
import com.example.warehouse.entity.Output;
import com.example.warehouse.entity.OutputProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OutputProductService extends ActiveCheckable {
    Page<OutputProduct> findAll(Pageable pageable);
    Page<OutputProduct> findAllByOutputId(Long outputId, Pageable pageable);
    OutputProduct findById(Long id);
    OutputProduct save(OutputProduct outputProduct);
    void deleteById(Long id);
    void setAttributes(OutputProductInnerDTO dto, OutputProduct outputProduct, Output output);

    List<MostOutputProductsDTO> findDailyMostOutputProducts();
}
