package com.example.warehouse.exception;

public class ProductOutOfStockException extends RuntimeException {
    public ProductOutOfStockException() {
    }

    public ProductOutOfStockException(String productName) {
        super(productName + " is out of stock");
    }
}
