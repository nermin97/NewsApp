package com.softraysolutions.news.controller;


import com.softraysolutions.news.model.User;
import com.softraysolutions.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/api/auth/login")
    public String login(@RequestBody User user){
        return user.toString();
    }

    @PostMapping("/api/auth/register")
    public String register(@RequestBody User user) {
        return user.toString();
    }

    @GetMapping("/api/auth")
    public User authorize() {
        return service.getAll().get(0);
    }

}
