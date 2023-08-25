package com.example.warehouse.service;

import com.example.warehouse.dto.OutputProductInnerDTO;
import com.example.warehouse.entity.Output;
import com.example.warehouse.entity.OutputProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OutputProductService {
    Page<OutputProduct> findAll(Pageable pageable);
    Page<OutputProduct> findAllByOutputId(Long outputId, Pageable pageable);
    OutputProduct findById(Long id);
    OutputProduct save(OutputProduct outputProduct);
    void deleteById(Long id);
    void deleteAllByOutputId(Long outputId);
    void setAttributes(OutputProductInnerDTO dto, OutputProduct outputProduct, Output output);
}
