package com.demosproject.springboot.carsbackendmvc.controllers;

import com.demosproject.springboot.carsbackendmvc.domain.model.User;
import com.demosproject.springboot.carsbackendmvc.services.UserService;
import java.util.List;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.Contended;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUserById(@PathVariable String id){
        return userService.getUsers();
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveUser(@PathVariable User user) {
        userService.saveUser(user);
    }
}
