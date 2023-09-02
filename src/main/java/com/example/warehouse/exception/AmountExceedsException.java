package com.example.warehouse.exception;

public class AmountExceedsException extends RuntimeException {
    public AmountExceedsException() {
    }

    public AmountExceedsException(Double remaining) {
        super("This amount exceeds the rest of the product. Amount of remaining product: " + remaining);
    }
}
