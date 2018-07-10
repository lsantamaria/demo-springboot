package com.demosproject.springboot.carsbackend.jpa.controllers;

import com.demosproject.springboot.carsbackend.jpa.dto.RaceDto;
import com.demosproject.springboot.carsbackend.jpa.dto.RaceFullDto;
import com.demosproject.springboot.carsbackend.jpa.services.RaceServiceJPA;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for operating with race entities.
 */
@RestController
@RequestMapping("/jpa")
public class RaceControllerJPA {

  private final RaceServiceJPA raceServiceJPA;

  public RaceControllerJPA(RaceServiceJPA raceServiceJPA) {
    this.raceServiceJPA = raceServiceJPA;
  }

  /**
   * Retrieve all the races and return its DTO simple representation.
   *
   * @return the list of Race DTOs.
   */
  @GetMapping(value = "/races", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<RaceDto> getRaces() {
    return raceServiceJPA.getRaces();
  }

  /**
   * Retrieve all the races of the given user and return a list of their DTO simple
   * representations.
   *
   * @return the list of Race DTOs.
   */
  @GetMapping(value = "/users/{userId}/races", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<RaceDto> getUserRaces(@PathVariable @NotNull String userId) {
    return raceServiceJPA.getUserRaces(Long.parseLong(userId));
  }

  /**
   * Retrieve a race with the given identifier and return its DTO representation.
   *
   * @param id the race identifier.
   * @return the race corresponding to the given identifier.
   */
  @GetMapping(value = "/races/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public RaceFullDto getRaceById(@PathVariable String id) {
    return raceServiceJPA.getRaceByID(Long.parseLong(id));
  }

  /**
   * Save a race.
   *
   * @param race the race to save.
   */
  @PostMapping(value = "/races", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void saveRace(@RequestBody RaceDto race) {
    raceServiceJPA.saveRace(race);
  }

}
