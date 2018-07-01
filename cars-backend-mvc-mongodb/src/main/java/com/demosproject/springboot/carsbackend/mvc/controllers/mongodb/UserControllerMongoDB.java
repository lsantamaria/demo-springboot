package com.demosproject.springboot.carsbackend.mvc.controllers.mongodb;

import com.demosproject.springboot.carsbackend.mvc.domain.model.User;
import com.demosproject.springboot.carsbackend.mvc.services.mongodb.UserServiceMongoDB;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongodb")
public class UserControllerMongoDB {

    private final UserServiceMongoDB userServiceMongoDB;

    public UserControllerMongoDB(UserServiceMongoDB userServiceMongoDB){
        this.userServiceMongoDB = userServiceMongoDB;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers(){
        return userServiceMongoDB.getUsers();
    }

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUserById(@PathVariable String id){
        return userServiceMongoDB.getUsers();
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveUser(@PathVariable User user) {
        userServiceMongoDB.saveUser(user);
    }
}
