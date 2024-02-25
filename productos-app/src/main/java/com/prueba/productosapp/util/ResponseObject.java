package com.prueba.productosapp.util;

import com.prueba.productosapp.models.ProductoModel;

public class ResponseObject {
    private int status;
    private String message;
    private ProductoModel objeto;

    // Constructor
    public ResponseObject(int status, String message, ProductoModel objeto) {
        this.status = status;
        this.message = message;
        this.objeto = objeto;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProductoModel getObjeto() {
        return objeto;
    }

    public void setObjeto(ProductoModel objeto) {
        this.objeto = objeto;
    }
}
