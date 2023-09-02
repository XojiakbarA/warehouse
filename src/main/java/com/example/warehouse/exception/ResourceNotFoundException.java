package com.example.warehouse.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String resource, Long id, String column) {
        super(resource + " not found with " + column + " = " + id);
    }

    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " not found with id = " + id);
    }
}
