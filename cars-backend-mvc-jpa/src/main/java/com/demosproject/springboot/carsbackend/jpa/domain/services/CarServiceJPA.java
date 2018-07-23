package com.demosproject.springboot.carsbackend.jpa.domain.services;

import com.demosproject.springboot.carsbackend.jpa.domain.model.Car;
import com.demosproject.springboot.carsbackend.jpa.domain.model.User;
import com.demosproject.springboot.carsbackend.jpa.dto.CarDto;
import com.demosproject.springboot.carsbackend.jpa.domain.repositories.CarRepositoryJPA;
import com.demosproject.springboot.carsbackend.jpa.domain.repositories.UserRepositoryJPA;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Data
@RequiredArgsConstructor
@Service
public class CarServiceJPA {

  private final CarRepositoryJPA carRepositoryJPA;

  private final UserRepositoryJPA userRepositoryJPA;

  private final ModelMapper modelMapper;

  /**
   * Retrieve all the cars of the given user.
   *
   * @param id the user ID.
   * @return the list of user cars.
   */
  public List<CarDto> getCarsByUserId(long id) {
    List<Car> cars = this.carRepositoryJPA.findByUser_Id(id);
    return cars.stream().map(car -> modelMapper.map(car, CarDto.class))
        .collect(Collectors.toList());
  }

  /**
   * Retrieve a car by the given id.
   *
   * @param id the id of the car to retrieve.
   * @return the DTO representation of the car.
   */
  public CarDto getCarById(Long id) {
    Optional<Car> carOptional = this.carRepositoryJPA.findById(id);
    if (carOptional.isPresent()) {
      return modelMapper.map(carOptional.get(), CarDto.class);
    } else {
      throw new NoSuchElementException(String.format("Car with id %d does not exist ", id));
    }
  }

  /**
   * Create a new car.
   *
   * @param carDto the car DTO representation.
   */
  public CarDto saveCar(long userId, CarDto carDto) {
    Optional<User> userOptional = userRepositoryJPA.findById(userId);

    if (userOptional.isPresent()) {
      Car car = modelMapper.map(carDto, Car.class);
      User user = userOptional.get();
      car = carRepositoryJPA.save(car);
      user.addCar(car);
      userRepositoryJPA.save(user);
      return modelMapper.map(car, CarDto.class);
    } else {
      throw new NoSuchElementException(
          String.format("Could not add a new car because user with id %d does not exist", userId));
    }

  }

  /**
   * Delete car by the given id.
   *
   * @param carId the car ID to delete.
   */
  public void deleteCar(long carId) {
    this.carRepositoryJPA.deleteById(carId);
  }
}
