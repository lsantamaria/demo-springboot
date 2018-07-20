package com.demosproject.springboot.carsbackend.jpa.services;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.demosproject.springboot.carsbackend.jpa.domain.Car;
import com.demosproject.springboot.carsbackend.jpa.dto.CarDto;
import com.demosproject.springboot.carsbackend.jpa.repositories.CarRepositoryJPA;
import com.demosproject.springboot.carsbackend.jpa.repositories.UserRepositoryJPA;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceJPATest {

  @Mock
  private CarRepositoryJPA carRepositoryJPA;

  @Mock
  private ModelMapper modelMapper;

  private UserRepositoryJPA userRepositoryJPA;

  private CarServiceJPA carServiceJPA;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    carServiceJPA = new CarServiceJPA(carRepositoryJPA,userRepositoryJPA, modelMapper);
  }

  @Test
  public void whenGetCarById_thenCarDtoIsReturned() {
    Car testCar = new Car("honda-", "blue", 110);
    CarDto carDto = new CarDto(testCar.getId(), testCar.getBrand(), testCar.getColor(),
        testCar.getPower());

    when(modelMapper.map(any(), any())).thenReturn(carDto);
    when(carRepositoryJPA.findById(any())).thenReturn(Optional.of(testCar));

    CarDto obtainedCar = carServiceJPA.getCarById(testCar.getId());

    Assert.assertEquals(obtainedCar.getBrand(), testCar.getBrand());
    Assert.assertEquals(obtainedCar.getColor(), testCar.getColor());
    Assert.assertEquals(obtainedCar.getPower(), testCar.getPower());
  }

  @Test(expected = NoSuchElementException.class)
  public void whenGetCarByNonExistingId_thenExceptionIsThrown() {
    Car testCar = new Car("honda-", "blue", 110);

    when(carRepositoryJPA.findById(any())).thenReturn(Optional.empty());

    carServiceJPA.getCarById(testCar.getId());
  }

  @Test
  public void whenGetCarsByUserId_thenCarDtoListIsReturned() {
    long userId = 12L;

    Car testCar1 = new Car(1, "honda-", "blue", 110);
    CarDto carDto1 = new CarDto(testCar1.getId(), testCar1.getBrand(), testCar1.getColor(),
        testCar1.getPower());

    Car testCar2 = new Car(2, "mazda-", "green", 105);
    CarDto carDto2 = new CarDto(testCar2.getId(), testCar2.getBrand(), testCar2.getColor(),
        testCar2.getPower());

    when(modelMapper.map(any(), any())).thenReturn(carDto1).thenReturn(carDto2);
    when(carRepositoryJPA.findByUser_Id(userId)).thenReturn(asList(testCar1, testCar2));

    List<CarDto> carDtoList = carServiceJPA.getCarsByUserId(userId);
    Set<CarDto> carDtoSet = new HashSet<>(carDtoList);

    Assert.assertEquals(carDtoSet.size(), 2);
    Assert.assertTrue(carDtoSet.contains(carDto1));
    Assert.assertTrue(carDtoSet.contains(carDto2));
  }
}
