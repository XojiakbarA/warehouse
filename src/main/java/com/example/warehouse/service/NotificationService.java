package com.example.warehouse.service;

import com.example.warehouse.entity.Notification;

public interface NotificationService {
    Notification save(Notification notification);
    void deleteByInputProductId(Long inputProductId);

    Boolean existsByInputProductId(Long inputProductId);
}
