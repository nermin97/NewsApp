package com.softraysolutions.news.model;


import com.softraysolutions.news.model.enumeration.Enumerations;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated
    @Column(nullable = false)
    private Enumerations.UserType userType;

    public User() {}

    public User(String email, String password, Enumerations.UserType userType) {
        this.setEmail(email);
        this.setPassword(password);
        this.setUserType(userType);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Enumerations.UserType getUserType() {
        return userType;
    }

    public void setUserType(Enumerations.UserType userType) {
        this.userType = userType;
    }
}
