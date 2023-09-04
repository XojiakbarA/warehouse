package com.example.warehouse.service;

import com.example.warehouse.entity.RemindBeforeOption;

import java.util.List;

public interface RemindBeforeOptionService {
    List<RemindBeforeOption> findAll();
    RemindBeforeOption findById(Long id);
    RemindBeforeOption save(RemindBeforeOption remindBeforeOption);
    void deleteById(Long id);
}
