package com.softraysolutions.api.models;

public class UserAuth {

    private String token;

    private String email;

    private String type;

    public UserAuth() {}

    public UserAuth(String token, String email, String type) {
        this.setToken(token);
        this.setEmail(email);
        this.setType(type);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
