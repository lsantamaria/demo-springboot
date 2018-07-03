package com.demosproject.springboot.carsbackend.mvc.repositories.mongodb;

import com.demosproject.springboot.carsbackend.mvc.domain.model.Race;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepositoryMongoDB extends MongoRepository<Race, Integer> {



}
