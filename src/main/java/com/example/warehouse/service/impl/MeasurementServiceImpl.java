package com.example.warehouse.service.impl;

import com.example.warehouse.dto.MeasurementDTO;
import com.example.warehouse.entity.Measurement;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.MeasurementRepository;
import com.example.warehouse.service.MeasurementService;
import com.example.warehouse.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Override
    public Page<Measurement> findAll(Pageable pageable) {
        return measurementRepository.findAll(pageable);
    }

    @Override
    public Measurement findById(Long id) {
        Supplier<ResourceNotFoundException> supplier = () -> {
            String message = Message.createNotFound(Measurement.class.getSimpleName(), id);
            return new ResourceNotFoundException(message);
        };
        return measurementRepository.findById(id).orElseThrow(supplier);
    }

    @Override
    public Measurement save(Measurement measurement) {
        return measurementRepository.save(measurement);
    }

    @Override
    public void deleteById(Long id) {
        measurementRepository.deleteById(id);
    }

    @Override
    public void setAttributes(MeasurementDTO dto, Measurement measurement) {
        if (dto.getName() != null) {
            measurement.setName(dto.getName());
        }
        if (dto.getActive() != null) {
            measurement.setActive(dto.getActive());
        }
    }
}
