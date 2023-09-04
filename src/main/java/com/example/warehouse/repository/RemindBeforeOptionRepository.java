package com.example.warehouse.repository;

import com.example.warehouse.entity.RemindBeforeOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RemindBeforeOptionRepository extends JpaRepository<RemindBeforeOption, Long> {
}
