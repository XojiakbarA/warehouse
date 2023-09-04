package com.example.warehouse.event;

import com.example.warehouse.entity.InputProduct;
import lombok.Getter;

@Getter
public class NearToExpireEvent {
    private final InputProduct inputProduct;
    public NearToExpireEvent(InputProduct inputProduct) {
        this.inputProduct = inputProduct;
    }
}
