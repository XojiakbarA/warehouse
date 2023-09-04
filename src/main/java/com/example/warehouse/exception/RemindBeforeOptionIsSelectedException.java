package com.example.warehouse.exception;

public class RemindBeforeOptionIsSelectedException extends RuntimeException {
    public RemindBeforeOptionIsSelectedException() {
    }

    public RemindBeforeOptionIsSelectedException(String value) {
        super(value + " option is selected. The selected option cannot be deleted");
    }
}
