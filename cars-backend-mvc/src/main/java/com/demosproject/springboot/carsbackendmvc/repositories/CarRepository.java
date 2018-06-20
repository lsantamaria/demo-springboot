package com.demosproject.springboot.carsbackendmvc.repositories;

import com.demosproject.springboot.carsbackendmvc.domain.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends MongoRepository<Car,Integer> {

}
