package com.example.warehouse.service;

import com.example.warehouse.dto.CategoryDTO;
import com.example.warehouse.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<Category> findAll(Pageable pageable);

    Category findById(Long id);

    Category save(Category category);

    void deleteById(Long id);

    void setAttributes(CategoryDTO dto, Category category);
}
