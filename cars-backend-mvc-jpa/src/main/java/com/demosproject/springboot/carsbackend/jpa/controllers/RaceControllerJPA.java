package com.demosproject.springboot.carsbackend.jpa.controllers;

import com.demosproject.springboot.carsbackend.jpa.domain.model.Race;
import com.demosproject.springboot.carsbackend.jpa.services.RaceServiceJPA;
import java.util.List;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jpa")
public class RaceControllerJPA {

    private final RaceServiceJPA raceServiceJPA;

    public RaceControllerJPA(RaceServiceJPA raceServiceJPA){
        this.raceServiceJPA = raceServiceJPA;
    }

    @GetMapping(value = "/races", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Race> getRaces(){
        return raceServiceJPA.getRaces();
    }

    @GetMapping(value = "/races/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Race> getRaceById(@PathVariable String id){
        return raceServiceJPA.getRaceByID(Integer.parseInt(id));
    }

    @PostMapping(value = "/races", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveRace(@PathVariable Race race) {
        raceServiceJPA.saveRace(race);
    }
}
