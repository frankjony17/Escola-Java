package com.example.test.exception;

public class ObjectMapperException extends Exception {

    public ObjectMapperException(String message) {
        super(message);
    }

    public ObjectMapperException(String message, Exception e) {
        super(message, e);
    }
}
