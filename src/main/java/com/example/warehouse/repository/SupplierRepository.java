package com.example.warehouse.repository;

import com.example.warehouse.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findAllByNameContainingIgnoreCase(String name);
}
