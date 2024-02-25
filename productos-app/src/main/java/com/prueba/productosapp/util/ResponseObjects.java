package com.prueba.productosapp.util;

import com.prueba.productosapp.models.ProductoModel;

import java.util.List;

public class ResponseObjects {

    private int status;
    private String message;
    private List<ProductoModel> objeto;

    // Constructor
    public ResponseObjects(int status, String message, List<ProductoModel> objeto) {
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

    public List<ProductoModel> getObjeto() {
        return objeto;
    }

    public void setObjeto(List<ProductoModel> objeto) {
        this.objeto = objeto;
    }
}
