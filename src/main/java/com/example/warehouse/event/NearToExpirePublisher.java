package com.example.warehouse.event;

import com.example.warehouse.entity.InputProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class NearToExpirePublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishNearToExpireEvent(InputProduct inputProduct) {
        applicationEventPublisher.publishEvent(new NearToExpireEvent(inputProduct));
    }
}
