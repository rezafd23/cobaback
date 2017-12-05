package com.example.rezafd.cobaback.Model;

/**
 * Created by REZAFD on 02/12/2017.
 */

public class SelectPassResponse {
    private String NRP, password, email;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getNRP() {
        return NRP;
    }

    public void setNRP(String NRP) {
        this.NRP = NRP;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
