package com.example.modeling.exception;

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = -8211985294044705097L;

    public CustomException(String message) {
        super(message);
    }
}
