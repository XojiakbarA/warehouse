package com.example.warehouse.service.impl;

import com.example.warehouse.entity.InputProduct;
import com.example.warehouse.entity.Notification;
import com.example.warehouse.event.NearToExpireEvent;
import com.example.warehouse.event.RemoveNearToExpireEvent;
import com.example.warehouse.repository.NotificationRepository;
import com.example.warehouse.service.InputProductService;
import com.example.warehouse.service.NotificationService;
import com.example.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private InputProductService inputProductService;
    @Autowired
    private UserService userService;

    @EventListener
    public void onApplicationEvent(NearToExpireEvent event) {
        InputProduct inputProduct = event.getInputProduct();
        if (inputProductService.isNearToExpire(inputProduct.getId())) {
            if (inputProduct.getRemaining() > 0 && !existsByInputProductId(inputProduct.getId())) {
                Notification notification = new Notification();
                String message = inputProduct.getProduct().getName() + " product is nearing expiration date";
                notification.setMessage(message);
                notification.setInputProduct(inputProduct);
                notification.setUsers(userService.findAll());
                save(notification);
            }
        }
    }

    @EventListener
    public void onApplicationEvent(RemoveNearToExpireEvent event) {
        InputProduct inputProduct = event.getInputProduct();
        if (inputProduct.getRemaining() < 0) {
            deleteByInputProductId(inputProduct.getId());
        }
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void deleteByInputProductId(Long inputProductId) {
        notificationRepository.deleteByInputProductId(inputProductId);
    }

    @Override
    public Boolean existsByInputProductId(Long inputProductId) {
        return notificationRepository.existsByInputProductId(inputProductId);
    }
}
