package com.demosproject.springboot.carsbackend.jpa.controllers;

import com.demosproject.springboot.carsbackend.jpa.dto.RaceDto;
import com.demosproject.springboot.carsbackend.jpa.dto.RaceFullDto;
import com.demosproject.springboot.carsbackend.jpa.domain.services.RaceServiceJPA;
import com.demosproject.springboot.carsbackend.jpa.dto.UserId;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequiredArgsConstructor
public class RaceControllerJPA extends BaseJPAControllers {

  static final String GET_ALL_RACES = BASE_PATH + "/races";

  static final String POST_NEW_RACE = BASE_PATH + "/races";

  static final String GET_RACES_BY_USER = BASE_PATH + "/users/{userId}/races";

  static final String GET_RACE_BY_ID = BASE_PATH + "/races/{raceId}";

  static final String DELETE_RACE = BASE_PATH + "/races/{raceId}";

  private static final String ADD_USER_TO_RACE = BASE_PATH + "/races/{raceId}/users" ;

  private final RaceServiceJPA raceServiceJPA;

  /**
   * Retrieves all the races and return its DTO simple representation.
   *
   * @return the list of Race DTOs.
   */
  @GetMapping(value = GET_ALL_RACES, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<RaceDto> getRaces() {
    return raceServiceJPA.getRaces();
  }

  /**
   * Retrieves all the races of the given user and return a list of their DTO simple
   * representations.
   *
   * @return the list of Race DTOs.
   */
  @GetMapping(value = GET_RACES_BY_USER, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<RaceDto> getUserRaces(@PathVariable @NotNull final String userId) {
    return raceServiceJPA.getUserRaces(Long.parseLong(userId));
  }

  /**
   * Retrieves a race with the given identifier and return its DTO representation.
   *
   * @param raceId the race identifier.
   * @return the race corresponding to the given identifier.
   */
  @GetMapping(value = GET_RACE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public RaceFullDto getRaceById(@PathVariable String raceId) {
    return raceServiceJPA.getRaceByID(Long.parseLong(raceId));
  }

  /**
   * x Saves a race.
   *
   * @param race the race to save.
   */
  @PostMapping(value = POST_NEW_RACE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public RaceDto saveRace(@RequestBody @Validated final RaceDto race,
      final Authentication authentication) {
    return raceServiceJPA.saveRace(race, (String) authentication.getPrincipal());
  }

  /**
   * Deletes a race.
   *
   * @param raceId the race id.
   */
  @DeleteMapping(value = DELETE_RACE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteRace(@PathVariable final String raceId) {
    raceServiceJPA.deleteRace(Long.parseLong(raceId));
  }

  /**
   * Adds a user to an existing race
   *
   * @param raceId the race id.
   */
  @PostMapping(value = ADD_USER_TO_RACE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void addUserToRace(@PathVariable final long raceId, @RequestBody final UserId userId,
      final Authentication authentication) {
    raceServiceJPA.addUserToRace(raceId, userId.getId() , (String) authentication.getPrincipal());
  }

}
