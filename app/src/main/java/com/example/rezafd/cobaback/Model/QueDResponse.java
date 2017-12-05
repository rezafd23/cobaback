package com.example.rezafd.cobaback.Model;

/**
 * Created by REZAFD on 05/12/2017.
 */

public class QueDResponse {
    private String message;
    private int value;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
