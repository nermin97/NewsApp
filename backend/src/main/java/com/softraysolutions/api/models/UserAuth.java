package com.softraysolutions.api.models;

public class UserAuth {

    private String token;

    private String email;

    public UserAuth() {}

    public UserAuth(String token, String email) {
        this.setToken(token);
        this.setEmail(email);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
