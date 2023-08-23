package com.example.warehouse.util;

public class Message {
    public static String createNotFound(String resource, Long id, String column) {
        return resource + " not found with " + column + " = " + id;
    }
    public static String createNotFound(String resource, Long id) {
        return resource + " not found with id = " + id;
    }
}
