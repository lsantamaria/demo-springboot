package com.demosproject.springboot.carsbackend.jpa.services;


import com.demosproject.springboot.carsbackend.jpa.domain.User;
import com.demosproject.springboot.carsbackend.jpa.repositories.UserRepositoryJPA;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Service
public class UserServiceJPA {

    private UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public UserServiceJPA(UserRepositoryJPA UserRepositoryJPA){
        this.userRepositoryJPA = UserRepositoryJPA;
    }

    public List<User> getUsers(){
        return this.userRepositoryJPA.findAll();

    }

    public Optional<User> getUserById(Long id){
        return this.userRepositoryJPA.findById(id);
    }

    public void saveUser(User car){
        this.userRepositoryJPA.save(car);
    }

}