package com.demosproject.springboot.carsbackendmvc.repositories;

import com.demosproject.springboot.carsbackendmvc.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {
}
