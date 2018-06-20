package com.demosproject.springboot.carsbackendmvc.repositories;

import com.demosproject.springboot.carsbackendmvc.domain.model.Race;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends MongoRepository<Race, Integer> {
}
