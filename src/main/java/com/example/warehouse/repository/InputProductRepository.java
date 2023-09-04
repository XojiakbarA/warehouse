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
    List<InputProduct> findAllByProductNameContainingIgnoreCase(String productName);


    @Query(value = "select (select (extract(epoch from expire_date - now())/86400) from input_products where id=?1) < (select value from remind_before where selected=true)", nativeQuery = true)
    Boolean checkByIdIsNearToExpire(Long id);
    @Query(nativeQuery = true)
    List<InputProduct> findAllNearToExpire();
    @Query(value = "select count(ip.id) from input_products ip where (extract(epoch from ip.expire_date - now())/86400) < (select value from remind_before where selected=true) and (ip.amount - (select coalesce(sum(amount), 0) from output_products where input_product_id=ip.id)) > 0;", nativeQuery = true)
    Long countNearToExpire();
    @Query(nativeQuery = true)
    List<TotalCostDTO> findDailyInputTotalCost();
    @Query(nativeQuery = true)
    List<TotalAmountDTO> findDailyInputTotalAmount();
}
