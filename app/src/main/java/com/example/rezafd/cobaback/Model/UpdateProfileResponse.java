package com.example.rezafd.cobaback.Model;

/**
 * Created by PROTECT-32 on 12/7/2017.
 */

public class UpdateProfileResponse {
    private String message;
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
