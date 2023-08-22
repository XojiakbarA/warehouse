package com.example.warehouse.service;

import com.example.warehouse.dto.MeasurementDTO;
import com.example.warehouse.entity.Measurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MeasurementService {
    Page<Measurement> findAll(Pageable pageable);

    Measurement findById(Long id);

    Measurement save(Measurement measurement);

    void deleteById(Long id);

    void setAttributes(MeasurementDTO dto, Measurement measurement);
}
