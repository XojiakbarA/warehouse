package com.example.warehouse.repository;

import com.example.warehouse.dto.dashboard.MostOutputProductsDTO;
import com.example.warehouse.entity.OutputProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutputProductRepository extends JpaRepository<OutputProduct, Long> {
    Page<OutputProduct> findAllByOutputId(Long outputId, Pageable pageable);
    void deleteAllByOutputId(Long outputId);

    @Query(nativeQuery = true)
    List<MostOutputProductsDTO> findDailyMostOutputProducts();
}
