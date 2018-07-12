package com.demosproject.springboot.carsbackend.jpa.controllers;

import com.demosproject.springboot.carsbackend.jpa.CarsBackendJPAApplication;
import com.demosproject.springboot.carsbackend.jpa.domain.Car;
import com.demosproject.springboot.carsbackend.jpa.domain.Race;
import com.demosproject.springboot.carsbackend.jpa.domain.User;
import com.demosproject.springboot.carsbackend.jpa.dto.RaceDto;
import com.demosproject.springboot.carsbackend.jpa.repositories.RaceRepositoryJPA;
import com.demosproject.springboot.carsbackend.jpa.repositories.UserRepositoryJPA;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = CarsBackendJPAApplication.class)
@TestPropertySource(
    locations = "classpath:application-integrationtest.properties")
@ActiveProfiles("test")
public class RaceControllerJPAITest {

  @Autowired
  private RaceRepositoryJPA raceRepositoryJPA;

  @Autowired
  private UserRepositoryJPA userRepositoryJPA;

  @Autowired
  private TestRestTemplate testRestTemplate;

  /**
   * Save some races and test that only the ones which the user belongs to are returned when getting
   * races by user.
   */
  @Test
  public void testGetRacesByUser() {
    User user1 = saveTestUser();
    User user2 = saveTestUser();

    Race race1 = saveTestRace(user1);
    Race race2 = saveTestRace(user2);
    Race race3 = saveTestRace(user2);

    ResponseEntity<RaceDto[]> responseEntity = testRestTemplate
        .getForEntity(RaceControllerJPA.GET_RACES_BY_USER, RaceDto[].class, user1.getId());
    RaceDto obtainedRace = Arrays.asList(responseEntity.getBody()).get(0);

    Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    Assert.assertEquals(obtainedRace.getName(), race1.getName());
    Assert.assertEquals(obtainedRace.getId(), race1.getId());

  }

  /**
   * Save 3 races and test that we are retrieving them correctly.
   */
  @Test
  public void testGetAllRaces() {
    User user = saveTestUser();
    User user2 = saveTestUser();
    User user3 = saveTestUser();

    Race race = saveTestRace(user);
    Race race2 = saveTestRace(user2);
    Race race3 = saveTestRace(user3);

    List<Race> savedRaces = asList(race, race2, race3);

    ResponseEntity<RaceDto[]> responseEntity = testRestTemplate
        .getForEntity(RaceControllerJPA.GET_ALL_RACES, RaceDto[].class);
    List<RaceDto> obtainedRaces = Arrays.asList(responseEntity.getBody());

    Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    Assert.assertEquals(obtainedRaces.size(), savedRaces.size());

  }

  private Race saveTestRace(User u) {
    Race race = new Race();
    race.setName("Race-" + System.nanoTime());
    race.setStartDate(LocalDate.now());
    race.addUser(u);

    return raceRepositoryJPA.save(race);
  }

  private User saveTestUser() {
    String userName = "User-" + System.nanoTime();
    User u = new User(userName, getTestCars());
    return userRepositoryJPA.save(u);
  }

  private Set<Car> getTestCars() {
    Car honda = new Car("honda", "blue", 110);
    Car mazda = new Car("mazda", "white", 180);
    return new HashSet<>(asList(honda, mazda));
  }
}
