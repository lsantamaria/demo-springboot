package com.demosproject.springboot.carsbackendmvc.services;


import com.demosproject.springboot.carsbackendmvc.domain.model.Race;
import com.demosproject.springboot.carsbackendmvc.repositories.RaceRepository;
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
public class RaceService {

    private RaceRepository raceRepository;
    @Autowired
    public RaceService(RaceRepository raceRepository){
        this.raceRepository = raceRepository;
    }
    public List<Race> getRaces(){
        return this.raceRepository.findAll();
    }

    public Optional<Race> getRaceByID(Integer id){
        return this.raceRepository.findById(id);
    }

    public void saveRace(Race car){
        this.raceRepository.save(car);
    }

}