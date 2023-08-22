package com.example.warehouse.service.impl;

import com.example.warehouse.dto.CategoryDTO;
import com.example.warehouse.entity.Category;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.CategoryRepository;
import com.example.warehouse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void setAttributes(CategoryDTO dto, Category category) {
        if (dto.getParentCategoryId() != null) {
            Category parentCategory = findById(dto.getParentCategoryId());
            category.setParentCategory(parentCategory);
        }
        if (dto.getName() != null) {
            category.setName(dto.getName());
        }
        if (dto.getActive() != null) {
            category.setActive(dto.getActive());
        }
    }

}
