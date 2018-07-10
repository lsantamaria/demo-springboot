package com.demosproject.springboot.carsbackend.jpa.controllers;

import com.demosproject.springboot.carsbackend.jpa.domain.RaceDto;
import com.demosproject.springboot.carsbackend.jpa.domain.RaceFullDto;
import com.demosproject.springboot.carsbackend.jpa.services.RaceServiceJPA;
import java.util.List;
import java.util.NoSuchElementException;
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
    public List<RaceDto> getRaces() {

        return raceServiceJPA.getRaces();
    }

    @GetMapping(value = "/races/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RaceFullDto getRaceById(@PathVariable String id) {

      Optional<RaceFullDto> raceFullDtoOptional = raceServiceJPA.getRaceByID(Long.parseLong(id));

      if (raceFullDtoOptional.isPresent()) {
        return raceFullDtoOptional.get();
      } else {
        throw new NoSuchElementException(String.format("Race with id %s does not exist", id));
      }
    }

    @PostMapping(value = "/races", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveRace(@PathVariable RaceFullDto race) {
        raceServiceJPA.saveRace(race);
    }
}
