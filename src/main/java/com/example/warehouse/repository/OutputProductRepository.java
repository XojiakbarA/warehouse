package com.example.warehouse.repository;

import com.example.warehouse.entity.OutputProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputProductRepository extends JpaRepository<OutputProduct, Long> {
    Page<OutputProduct> findAllByOutputId(Long outputId, Pageable pageable);
    void deleteAllByOutputId(Long outputId);
}
