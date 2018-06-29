package com.demosproject.springboot.carsbackend.mvc.repositories.mongodb;

import com.demosproject.springboot.carsbackend.mvc.domain.model.User;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryMongoDB extends MongoRepository<User,Integer> {

    List<User> findByName(String name);



}
