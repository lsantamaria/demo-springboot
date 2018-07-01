package com.demosproject.springboot.carsbackend.jpa;

import com.demosproject.springboot.carsbackend.jpa.controllers.RaceControllerJPA;
import com.demosproject.springboot.carsbackend.jpa.domain.model.Car;
import com.demosproject.springboot.carsbackend.jpa.domain.model.Race;
import com.demosproject.springboot.carsbackend.jpa.domain.model.User;
import com.demosproject.springboot.carsbackend.jpa.repositories.CarRepositoryJPA;
import com.demosproject.springboot.carsbackend.jpa.repositories.RaceRepositoryJPA;
import com.demosproject.springboot.carsbackend.jpa.repositories.UserRepositoryJPA;
import com.demosproject.springboot.carsbackend.jpa.services.RaceServiceJPA;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
classes = CarsBackendJPAApplication.class)
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class RaceControllerJPAIntegrationTest {


    @Autowired
    RaceRepositoryJPA raceRepositoryJPA;

    @Autowired
    CarRepositoryJPA carRepositoryJPA;

    @Autowired
    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    RaceServiceJPA raceServiceJPA;


    @Autowired
    TestRestTemplate testRestTemplate;

    @Before
    public void saveMockData(){
        for(int i= 0 ; i< 200 ; i++) {
            Car honda = new Car("honda-" + i, "blue", 75);
            Car mazda = new Car("seat-" + i, "black", 34);
            Car ford = new Car("ford-" + i, "blue", 75);
            Car audi = new Car("audi-" + i, "black", 34);

            carRepositoryJPA.save(honda);
            carRepositoryJPA.save(mazda);
            carRepositoryJPA.save(ford);
            carRepositoryJPA.save(audi);

            User user = new User("User " + i, asList(honda, mazda));
            //User albert = new User("Albert",asList(ford,audi));

            userRepositoryJPA.save(user);
            //userRepositoryMongoDB.save(albert);

            Race race = new Race();
            race.setName("Race number %d " + i);
            race.setStartDate(Calendar.getInstance().getTime());
            race.setUsers(asList(user));

            raceRepositoryJPA.save(race);
        }
    }


    @Test
    public void whenFindRaces_thenReturnAllRaces() throws Exception {

        Instant instant = Instant.now();
        /*ResponseEntity<Object[]> responseEntity = */
        testRestTemplate.getForEntity("/jpa/races", Object[].class);
        Instant instant1 = Instant.now();

        long duration = Duration.between(instant,instant1).toNanos() / 100000000;

        System.err.println(String.format("Races JPA query duration: %d seconds",duration));






    }

}
