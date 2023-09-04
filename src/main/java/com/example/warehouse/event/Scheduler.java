package com.example.warehouse.event;

import com.example.warehouse.entity.InputProduct;
import com.example.warehouse.service.InputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    @Autowired
    private NearToExpirePublisher nearToExpirePublisher;
    @Autowired
    private RemoveNearToExpirePublisher removeNearToExpirePublisher;
    @Autowired
    private InputProductService inputProductService;

    @Scheduled(cron = "@daily")
    public void check() {
        for (InputProduct inputProduct : inputProductService.findAllNearToExpire()) {
            nearToExpirePublisher.publishNearToExpireEvent(inputProduct);
            removeNearToExpirePublisher.publishNearToExpireEvent(inputProduct);
        }
    }
}
