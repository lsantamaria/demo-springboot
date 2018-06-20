package com.demosproject.springboot.carsbackendmvc.services;


import com.demosproject.springboot.carsbackendmvc.domain.model.User;
import com.demosproject.springboot.carsbackendmvc.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id){
        return this.userRepository.findById(id);
    }

    public void saveUser(User car){
        this.userRepository.save(car);
    }

}