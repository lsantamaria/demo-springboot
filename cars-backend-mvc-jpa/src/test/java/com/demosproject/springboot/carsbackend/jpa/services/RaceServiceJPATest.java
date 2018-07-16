/*
package com.demosproject.springboot.carsbackend.jpa.services;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.demosproject.springboot.carsbackend.jpa.domain.Car;
import com.demosproject.springboot.carsbackend.jpa.domain.Race;
import com.demosproject.springboot.carsbackend.jpa.domain.User;
import com.demosproject.springboot.carsbackend.jpa.dto.RaceDto;
import com.demosproject.springboot.carsbackend.jpa.repositories.RaceRepositoryJPA;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class RaceServiceJPATest {

  @Mock
  private ModelMapper modelMapper;

  @Mock
  private RaceRepositoryJPA raceRepositoryJPA;

  @InjectMocks
  private RaceServiceJPA raceServiceJPA;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void whenGetRaces_thenRaceDtoListIsReturned() {

    Car honda = new Car("honda-", "blue", 110);
    Car mazda = new Car("mazda-", "white", 180);
    Car ford = new Car("ford-", "blue", 105);
    Car audi = new Car("audi-", "black", 200);

    User user1 = new User();
    user1.setName("user1");
    user1.addCar(honda);
    user1.addCar(mazda);

    User user2 = new User();
    user2.setName("user2");
    user2.addCar(ford);
    user2.addCar(audi);

    Race race1 = new Race(1, "Race1", LocalDate.now(),
        new HashSet<>(Collections.singletonList(user1)));
    Race race2 = new Race(2, "Race2", LocalDate.now(),
        new HashSet<>(Collections.singletonList(user2)));

    RaceDto race1Dto = new RaceDto(race1.getId(), race1.getName(), race1.getStartDate().toString());
    RaceDto race2Dto = new RaceDto(race2.getId(), race2.getName(), race2.getStartDate().toString());

    when(raceRepositoryJPA.findAll()).thenReturn(asList(race1, race2));
    when(modelMapper.map(any(), any())).thenReturn(race1Dto).thenReturn(race2Dto);

    List<RaceDto> raceDtoList = raceServiceJPA.getRaces();
    Set<RaceDto> raceDtoSet = new HashSet<>(raceDtoList);

    Assert.assertTrue(raceDtoSet.contains(race1Dto));
    Assert.assertTrue(raceDtoSet.contains(race2Dto));

  }

}
*/
