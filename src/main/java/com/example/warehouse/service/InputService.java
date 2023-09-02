package com.example.warehouse.service;

import com.example.warehouse.dto.InputUpdateDTO;
import com.example.warehouse.entity.Input;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InputService extends ActiveCheckable {
    Page<Input> findAll(Pageable pageable);

    Input findById(Long id);

    Input save(Input input);

    void deleteById(Long id);

    void setAttributes(InputUpdateDTO dto, Input input);
}
