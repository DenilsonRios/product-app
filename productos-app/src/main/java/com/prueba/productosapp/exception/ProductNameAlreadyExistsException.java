package com.prueba.productosapp.exception;

public class ProductNameAlreadyExistsException extends RuntimeException {
    public ProductNameAlreadyExistsException(String message) {
        super(message);
    }
}
