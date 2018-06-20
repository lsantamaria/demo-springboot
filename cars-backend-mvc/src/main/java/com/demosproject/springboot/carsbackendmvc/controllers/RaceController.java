package com.demosproject.springboot.carsbackendmvc.controllers;

import com.demosproject.springboot.carsbackendmvc.domain.model.Race;
import com.demosproject.springboot.carsbackendmvc.services.RaceService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService RaceService){
        this.raceService = RaceService;
    }

    @GetMapping(value = "/races", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Race> getRaces(){
        return raceService.getRaces();
    }

    @GetMapping(value = "/races/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Race> getRaceById(@PathVariable String id){
        return raceService.getRaceByID(Integer.parseInt(id));
    }

    @PostMapping(value = "/races", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveRace(@PathVariable Race race) {
        raceService.saveRace(race);
    }
}
