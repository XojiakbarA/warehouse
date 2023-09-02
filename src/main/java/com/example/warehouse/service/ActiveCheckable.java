package com.example.warehouse.service;

import com.example.warehouse.entity.Activable;
import com.example.warehouse.exception.NotActiveException;

public interface ActiveCheckable {
    default void checkActive(Activable activable) {
        if (!activable.getActive()) {
            throw new NotActiveException(activable.getClass().getSimpleName(), activable.getId());
        }
    }
}
