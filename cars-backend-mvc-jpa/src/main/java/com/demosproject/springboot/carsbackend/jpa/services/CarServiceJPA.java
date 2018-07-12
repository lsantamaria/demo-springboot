package com.demosproject.springboot.carsbackend.jpa.services;

import com.demosproject.springboot.carsbackend.jpa.domain.Car;
import com.demosproject.springboot.carsbackend.jpa.domain.User;
import com.demosproject.springboot.carsbackend.jpa.dto.CarDto;
import com.demosproject.springboot.carsbackend.jpa.repositories.CarRepositoryJPA;
import com.demosproject.springboot.carsbackend.jpa.repositories.UserRepositoryJPA;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Service
public class CarServiceJPA {

  private CarRepositoryJPA carRepositoryJPA;

  private UserRepositoryJPA userRepositoryJPA;

  private ModelMapper modelMapper;

  @Autowired
  public CarServiceJPA(CarRepositoryJPA carRepositoryJPA, UserRepositoryJPA userRepositoryJPA,
      ModelMapper modelMapper) {
    this.carRepositoryJPA = carRepositoryJPA;
    this.userRepositoryJPA = userRepositoryJPA;
    this.modelMapper = modelMapper;
  }

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
  public void saveCar(long userId, CarDto carDto) {
    Optional<User> userOptional = userRepositoryJPA.findById(userId);

    if (userOptional.isPresent()) {
      Car car = modelMapper.map(carDto, Car.class);
      User user = userOptional.get();
      user.addCar(car);
      userRepositoryJPA.save(user);
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
