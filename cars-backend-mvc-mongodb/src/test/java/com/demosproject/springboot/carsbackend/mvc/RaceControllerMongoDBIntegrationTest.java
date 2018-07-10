package com.demosproject.springboot.carsbackend.mvc;


import com.demosproject.springboot.carsbackend.mvc.domain.model.Car;
import com.demosproject.springboot.carsbackend.mvc.domain.model.Race;
import com.demosproject.springboot.carsbackend.mvc.domain.model.User;
import com.demosproject.springboot.carsbackend.mvc.repositories.mongodb.CarRepositoryMongoDB;
import com.demosproject.springboot.carsbackend.mvc.repositories.mongodb.RaceRepositoryMongoDB;
import com.demosproject.springboot.carsbackend.mvc.repositories.mongodb.UserRepositoryMongoDB;
import com.demosproject.springboot.carsbackend.mvc.services.mongodb.RaceServiceMongoDB;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CarsBackendMongoDBApplication.class)
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class RaceControllerMongoDBIntegrationTest {


    @Autowired
    RaceRepositoryMongoDB raceRepositoryMongoDB;

    @Autowired
    CarRepositoryMongoDB carRepositoryMongoDB;

    @Autowired
    UserRepositoryMongoDB userRepositoryMongoDB;

    @Autowired
    RaceServiceMongoDB raceServiceMongoDB;


    @Autowired
    TestRestTemplate testRestTemplate;

    @Before
    public void saveMockData(){

        for(int i= 0 ; i< 200 ; i++) {
            Car honda = new Car("honda-" + i, "blue", 75);
            Car mazda = new Car("seat-" + i, "black", 34);
            Car ford = new Car("ford-" + i, "blue", 75);
            Car audi = new Car("audi-" + i, "black", 34);

            carRepositoryMongoDB.save(honda);
            carRepositoryMongoDB.save(mazda);
            carRepositoryMongoDB.save(ford);
            carRepositoryMongoDB.save(audi);

            User user = new User("User " + i, asList(honda, mazda));
            //User albert = new User("Albert",asList(ford,audi));

            userRepositoryMongoDB.save(user);
            //userRepositoryMongoDB.save(albert);

            Race race = new Race();
            race.setName("Race number %d " + i);
            race.setStartDate(Calendar.getInstance().getTime());
            race.setUsers(asList(user));

            raceRepositoryMongoDB.save(race);
        }
    }


    @Test
    public void whenFindRaces_thenReturnAllRaces() throws Exception {

        Instant instant = Instant.now();
        testRestTemplate.getForEntity("/mongodb/races", Object[].class);
        Instant instant1 = Instant.now();

        long duration = Duration.between(instant,instant1).toNanos() / 100000000;

        System.out.println(String.format("Races query duration: %d seconds",duration));


    }

}
