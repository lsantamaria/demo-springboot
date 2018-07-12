package com.demosproject.springboot.carsbackend.jpa.controllers;

import com.demosproject.springboot.carsbackend.jpa.dto.RaceDto;
import com.demosproject.springboot.carsbackend.jpa.dto.RaceFullDto;
import com.demosproject.springboot.carsbackend.jpa.dto.UserId;
import com.demosproject.springboot.carsbackend.jpa.services.RaceServiceJPA;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for operating with race entities.
 */
@RestController
public class RaceControllerJPA extends BaseJPAControllers {

  static final String GET_ALL_RACES = BASE_PATH + "/races";

  static final String POST_NEW_RACE = BASE_PATH + "/races";

  static final String GET_RACES_BY_USER = BASE_PATH + "/users/{userId}/races";

  static final String GET_RACE_BY_ID = BASE_PATH + "/races/{id}";

  private RaceServiceJPA raceServiceJPA;

  public RaceControllerJPA(RaceServiceJPA raceServiceJPA) {
    this.raceServiceJPA = raceServiceJPA;
  }

  /**
   * Retrieve all the races and return its DTO simple representation.
   *
   * @return the list of Race DTOs.
   */
  @GetMapping(value = GET_ALL_RACES, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<RaceDto> getRaces() {
    return raceServiceJPA.getRaces();
  }

  /**
   * Retrieve all the races of the given user and return a list of their DTO simple
   * representations.
   *
   * @return the list of Race DTOs.
   */
  @GetMapping(value = GET_RACES_BY_USER, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<RaceDto> getUserRaces(@PathVariable @NotNull String userId) {
    return raceServiceJPA.getUserRaces(Long.parseLong(userId));
  }

  /**
   * Retrieve a race with the given identifier and return its DTO representation.
   *
   * @param id the race identifier.
   * @return the race corresponding to the given identifier.
   */
  @GetMapping(value = GET_RACE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public RaceFullDto getRaceById(@PathVariable String id) {
    return raceServiceJPA.getRaceByID(Long.parseLong(id));
  }

  /**
   * Adds a user to the current race
   */
  @PostMapping(value = "/races/{raceId}/users", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void addUserToRace(@PathVariable String raceId, @RequestBody UserId userId) {
    raceServiceJPA.addUserToRace(Long.parseLong(raceId), Long.parseLong(userId.getId()));
  }

  /**
   * Save a race.
   *
   * @param race the race to save.
   */
  @PostMapping(value = POST_NEW_RACE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void saveRace(@RequestBody RaceDto race) {
    raceServiceJPA.saveRace(race);
  }

}
