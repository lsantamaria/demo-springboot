package com.demosproject.springboot.carsbackend.mvc.services.mongodb;


import com.demosproject.springboot.carsbackend.mvc.domain.model.User;
import com.demosproject.springboot.carsbackend.mvc.repositories.mongodb.UserRepositoryMongoDB;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Service
public class UserServiceMongoDB {

    private UserRepositoryMongoDB userRepositoryMongoDB;

    @Autowired
    public UserServiceMongoDB(UserRepositoryMongoDB userRepositoryMongoDB){
        this.userRepositoryMongoDB = userRepositoryMongoDB;
    }

    public List<User> getUsers(){
        return this.userRepositoryMongoDB.findAll();

    }

    public Optional<User> getUserById(Integer id){
        return this.userRepositoryMongoDB.findById(id);
    }

    public void saveUser(User car){
        this.userRepositoryMongoDB.save(car);
    }

}