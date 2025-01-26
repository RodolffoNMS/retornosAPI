package com.example.retornosAPI.exception;

public class ProductNotFoundException extends RuntimeException {


    public ProductNotFoundException(String message) {
        super(message);
    }
}