package com.demosproject.springboot.carsbackend.mvc.controllers.mongodb;

import com.demosproject.springboot.carsbackend.mvc.domain.model.Race;
import com.demosproject.springboot.carsbackend.mvc.services.mongodb.RaceServiceMongoDB;
import java.util.List;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongodb")
public class RaceControllerMongoDB {

    private final RaceServiceMongoDB raceServiceMongoDB;

    public RaceControllerMongoDB(RaceServiceMongoDB RaceServiceMongoDB){
        this.raceServiceMongoDB = RaceServiceMongoDB;
    }

    @GetMapping(value = "/races", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Race> getRaces(){
        return raceServiceMongoDB.getRaces();
    }

    @GetMapping(value = "/races/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Race> getRaceById(@PathVariable String id){
        return raceServiceMongoDB.getRaceByID(Integer.parseInt(id));
    }

    @PostMapping(value = "/races", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveRace(@PathVariable Race race) {
        raceServiceMongoDB.saveRace(race);
    }
}
