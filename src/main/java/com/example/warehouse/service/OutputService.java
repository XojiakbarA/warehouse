package com.example.warehouse.service;

import com.example.warehouse.dto.OutputUpdateDTO;
import com.example.warehouse.entity.Output;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OutputService {
    Page<Output> findAll(Pageable pageable);

    Output findById(Long id);

    Output save(Output output);

    void deleteById(Long id);

    void setAttributes(OutputUpdateDTO dto, Output output);
}
