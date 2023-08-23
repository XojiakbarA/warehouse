package com.example.warehouse.repository;

import com.example.warehouse.entity.InputProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputProductRepository extends JpaRepository<InputProduct, Long> {
    Page<InputProduct> findAllByInputId(Long id, Pageable pageable);
    void deleteAllByInputId(Long inputId);
}
