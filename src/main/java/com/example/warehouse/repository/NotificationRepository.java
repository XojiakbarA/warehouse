package com.example.warehouse.repository;

import com.example.warehouse.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    void deleteByInputProductId(Long inputProductId);

    Boolean existsByInputProductId(Long inputProductId);
}
