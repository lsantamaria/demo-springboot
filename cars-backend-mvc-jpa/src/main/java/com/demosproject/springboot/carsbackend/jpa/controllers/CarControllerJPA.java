package com.demosproject.springboot.carsbackend.jpa.controllers;

import com.demosproject.springboot.carsbackend.jpa.dto.CarDto;
import com.demosproject.springboot.carsbackend.jpa.services.CarServiceJPA;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for retrieving car objects.
 */
@RestController
@RequestMapping("/jpa")
public class CarControllerJPA {

  private final CarServiceJPA carServiceJPA;

  @Autowired
  public CarControllerJPA(CarServiceJPA carServiceJPA) {
    this.carServiceJPA = carServiceJPA;
  }

  /**
   * Retrieve the car that has the given id.
   *
   * @param id the car id.
   * @return the DTO representation of the retrieved car.
   */
  @GetMapping(value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CarDto getCarById(@PathVariable String id) {
    validateParams(id);
    return carServiceJPA.getCarById(Long.parseLong(id));
  }

  /**
   * Retrieve all the cars of the given user.
   *
   * @param userId the id of the user.
   */
  @GetMapping(value = "/users/{userId}/cars", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<CarDto> getCarsByUserId(@PathVariable String userId) {
    validateParams(userId);
    return carServiceJPA.getCarsByUserId(Long.parseLong(userId));
  }

  /**
   * Endpoint for adding a new car.
   *
   * @param userId the user id that is adding the car.
   * @param carDto the new car DTO representation.
   */
  @PostMapping(value = "/users/{userId}/cars", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void saveCar(@PathVariable String userId, @Validated @RequestBody CarDto carDto) {
    validateParams(userId);
    carServiceJPA.saveCar(Long.parseLong(userId), carDto);
  }

  private void validateParams(String id) {
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("User id is null or empty");
    }
  }
}
