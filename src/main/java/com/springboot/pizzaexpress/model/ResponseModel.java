package com.springboot.pizzaexpress.model;

public class ResponseModel {
    private String status;
    private String message;
    private Object model;

    public Object getModel() {
        return model;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setModel(Object model) {
        this.model = model;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
