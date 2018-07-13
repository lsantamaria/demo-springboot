package com.demosproject.springboot.carsbackend.jpa.controllers;

import com.demosproject.springboot.carsbackend.jpa.dto.CarDto;
import com.demosproject.springboot.carsbackend.jpa.services.CarServiceJPA;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for retrieving car objects.
 */
@RestController
public class CarControllerJPA extends BaseJPAControllers {

  static final String GET_CAR_BY_ID = BASE_PATH + "/cars/{carId}";

  static final String GET_USER_CARS = BASE_PATH + "/users/{userId}/cars";

  static final String ADD_CAR = BASE_PATH + "/users/{userId}/cars";

  static final String DELETE_CAR = BASE_PATH + "/cars/{carId}";

  private final CarServiceJPA carServiceJPA;

  @Autowired
  public CarControllerJPA(CarServiceJPA carServiceJPA) {
    this.carServiceJPA = carServiceJPA;
  }

  /**
   * Retrieve the car that has the given id.
   *
   * @param carId the car id.
   * @return the DTO representation of the retrieved car.
   */
  @GetMapping(value = GET_CAR_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public CarDto getCarById(@PathVariable String carId) {
    validateParams(carId);
    return carServiceJPA.getCarById(Long.parseLong(carId));
  }

  /**
   * Retrieve all the cars of the given user.
   *
   * @param userId the id of the user.
   */
  @GetMapping(value = GET_USER_CARS, produces = MediaType.APPLICATION_JSON_VALUE)
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
  @PostMapping(value = ADD_CAR, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public CarDto saveCar(@PathVariable String userId, @Validated @RequestBody CarDto carDto) {
    validateParams(userId);
    return carServiceJPA.saveCar(Long.parseLong(userId), carDto);
  }

  /**
   * Deletes a car.
   *
   * @param carId the car ID to delete.
   */
  @DeleteMapping(value = DELETE_CAR)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCar(@PathVariable String carId) {
    this.carServiceJPA.deleteCar(Long.parseLong(carId));
  }

  private void validateParams(String id) {
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("User id is null or empty");
    }
  }
}
