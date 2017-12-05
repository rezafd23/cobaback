package com.example.rezafd.cobaback.Model;

/**
 * Created by REZAFD on 01/12/2017.
 */

public class ChangePassResponse {
    private String message;
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
}
