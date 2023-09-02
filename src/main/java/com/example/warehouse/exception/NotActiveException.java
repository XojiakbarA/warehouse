package com.example.warehouse.exception;

public class NotActiveException extends RuntimeException {
    public NotActiveException() {
    }

    public NotActiveException(String resourceName, Long id) {
        super("Selected " + resourceName + " with id=" + id + " is not active");
    }
}
