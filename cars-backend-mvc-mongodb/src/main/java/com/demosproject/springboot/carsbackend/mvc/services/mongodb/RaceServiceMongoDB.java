package com.demosproject.springboot.carsbackend.mvc.services.mongodb;


import com.demosproject.springboot.carsbackend.mvc.domain.model.Race;
import com.demosproject.springboot.carsbackend.mvc.repositories.mongodb.RaceRepositoryMongoDB;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Service
public class RaceServiceMongoDB {

    private RaceRepositoryMongoDB raceRepositoryMongoDB;

    @Autowired
    public RaceServiceMongoDB(RaceRepositoryMongoDB raceRepositoryMongoDB){
        this.raceRepositoryMongoDB = raceRepositoryMongoDB;
    }

    public List<Race> getRaces(){
        return this.raceRepositoryMongoDB.findAll();
    }

    public Optional<Race> getRaceByID(Integer id){
        return this.raceRepositoryMongoDB.findById(id);
    }

    public void saveRace(Race car){
        this.raceRepositoryMongoDB.save(car);
    }

}