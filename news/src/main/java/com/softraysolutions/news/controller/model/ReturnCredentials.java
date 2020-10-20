package com.softraysolutions.news.controller.model;

import com.softraysolutions.news.model.User;

public class ReturnCredentials {
    private User user;
    private String token;

    public ReturnCredentials() {}

    public ReturnCredentials(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "{" +
               "\"user\":" + user.toString() +
                ",\"token\":\"" + token +"\"" +
                '}';
    }
}
