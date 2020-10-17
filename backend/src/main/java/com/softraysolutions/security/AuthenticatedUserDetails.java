package com.softraysolutions.security;

import java.security.Principal;

public class AuthenticatedUserDetails implements Principal {


    private int id;
    private String email;

    public AuthenticatedUserDetails(int id, String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public String getName() {
        return getEmail();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
