package com.example.warehouse.repository;

import com.example.warehouse.dto.dashboard.TotalAmountDTO;
import com.example.warehouse.dto.dashboard.TotalCostDTO;
import com.example.warehouse.entity.InputProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputProductRepository extends JpaRepository<InputProduct, Long> {
    Page<InputProduct> findAllByInputId(Long id, Pageable pageable);
    void deleteAllByInputId(Long inputId);

    @Query(nativeQuery = true)
    List<TotalCostDTO> findDailyInputTotalCost();
    @Query(nativeQuery = true)
    List<TotalAmountDTO> findDailyInputTotalAmount();
}
