package com.softraysolutions.news.model;


import com.softraysolutions.news.model.enumeration.Enumerations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user", schema = "public")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated
    @Column(nullable = false)
    private Enumerations.UserType userType;

    public User() {}

    public User(String email, String password, Enumerations.UserType userType) {
        this.setUsername(email);
        this.setPassword(password);
        this.setUserType(userType);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (this.getUserType().equals(Enumerations.UserType.SuperAdmin)) {
            authorities.add(new SimpleGrantedAuthority(Enumerations.UserType.Admin.name()));
        }
        authorities.add(new SimpleGrantedAuthority(this.getUserType().name()));
        return authorities;
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

    @Override
    public String toString() {
        return "{ \"username\":\"" + this.getUsername() + "\",\"userType\":\"" + this.getUserType() + "\"}";
    }
}
