package com.demosproject.springboot.carsbackend.mvc.repositories.mongodb;

import com.demosproject.springboot.carsbackend.mvc.domain.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepositoryMongoDB extends MongoRepository<Car,Integer> {

}
