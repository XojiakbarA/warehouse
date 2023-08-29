package com.example.warehouse.repository;

import com.example.warehouse.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByNameContainingIgnoreCase(String name);
}
