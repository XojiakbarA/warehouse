package com.example.warehouse.event;

import com.example.warehouse.entity.InputProduct;
import lombok.Getter;

@Getter
public class RemoveNearToExpireEvent {
    private final InputProduct inputProduct;
    public RemoveNearToExpireEvent(InputProduct inputProduct) {
        this.inputProduct = inputProduct;
    }
}
