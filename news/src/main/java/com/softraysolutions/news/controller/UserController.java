package com.softraysolutions.news.controller;


import com.softraysolutions.news.model.User;
import com.softraysolutions.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/api/auth/register")
    public User register(@RequestBody User user) {
        return service.save(user);
    }

    @GetMapping("/api/auth")
    public Authentication authorize() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
