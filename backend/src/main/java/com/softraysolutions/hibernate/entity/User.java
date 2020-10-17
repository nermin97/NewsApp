package com.softraysolutions.hibernate.entity;


import com.softraysolutions.hibernate.entity.enumeration.Enumerations;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Email cannot be null")
    @Column(name = "email", unique = true)
    private String email;

    @NotNull(message = "Password cannot be null")
    @Column(name = "password")
    private String password;

    @Enumerated
    @NotNull(message = "userType can not be null")
    @Column
    private Enumerations.UserType userType;

    public User() {}

    public User(String email, String password, Enumerations.UserType userType) {
        this.setEmail(email);
        this.setPassword(password);
        this.setUserType(userType);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

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

    @Override
    public String toString() {
        return "{}";
    }

    public Enumerations.UserType getUserType() {
        return userType;
    }

    public void setUserType(Enumerations.UserType userType) {
        this.userType = userType;
    }
}
