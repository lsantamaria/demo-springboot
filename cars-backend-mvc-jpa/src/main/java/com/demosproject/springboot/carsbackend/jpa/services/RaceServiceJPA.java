package com.demosproject.springboot.carsbackend.jpa.services;


import com.demosproject.springboot.carsbackend.jpa.domain.model.Race;
import com.demosproject.springboot.carsbackend.jpa.repositories.RaceRepositoryJPA;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Service
public class RaceServiceJPA {

    private RaceRepositoryJPA raceRepositoryJPA;

    @Autowired
    public RaceServiceJPA(RaceRepositoryJPA raceRepositoryJPA){
        this.raceRepositoryJPA = raceRepositoryJPA;
    }

    public List<Race> getRaces(){
        return this.raceRepositoryJPA.findAll();
    }

    public Optional<Race> getRaceByID(Integer id){
        return this.raceRepositoryJPA.findById(id);
    }

    public void saveRace(Race car){
        this.raceRepositoryJPA.save(car);
    }

}