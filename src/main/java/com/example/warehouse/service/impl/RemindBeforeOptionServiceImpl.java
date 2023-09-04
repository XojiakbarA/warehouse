package com.example.warehouse.service.impl;

import com.example.warehouse.entity.RemindBeforeOption;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.RemindBeforeOptionRepository;
import com.example.warehouse.service.RemindBeforeOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemindBeforeOptionServiceImpl implements RemindBeforeOptionService {

    @Autowired
    private RemindBeforeOptionRepository remindBeforeOptionRepository;

    @Override
    public List<RemindBeforeOption> findAll() {
        return remindBeforeOptionRepository.findAll();
    }

    @Override
    public RemindBeforeOption findById(Long id) {
        return remindBeforeOptionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RemindBeforeOption.class.getSimpleName(), id)
        );
    }

    @Override
    public RemindBeforeOption save(RemindBeforeOption remindBeforeOption) {
        return remindBeforeOptionRepository.save(remindBeforeOption);
    }

    @Override
    public void deleteById(Long id) {
        remindBeforeOptionRepository.deleteById(id);
    }
}
