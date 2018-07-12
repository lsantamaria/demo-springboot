package com.demosproject.springboot.carsbackend.jpa.bootstrap;

import com.demosproject.springboot.carsbackend.jpa.domain.Car;
import com.demosproject.springboot.carsbackend.jpa.domain.Race;
import com.demosproject.springboot.carsbackend.jpa.domain.User;
import com.demosproject.springboot.carsbackend.jpa.repositories.CarRepositoryJPA;
import com.demosproject.springboot.carsbackend.jpa.repositories.RaceRepositoryJPA;
import com.demosproject.springboot.carsbackend.jpa.repositories.UserRepositoryJPA;
import java.time.LocalDate;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapCLR implements CommandLineRunner {

    private static final int NUMBER_OF_TEST_ENTITIES = 20;
    private final UserRepositoryJPA userRepositoryJPA;
    private final CarRepositoryJPA carRepositoryJPA;
    private final RaceRepositoryJPA raceRepositoryJPA;

    @Autowired
    public BootstrapCLR(UserRepositoryJPA userRepositoryJPA, CarRepositoryJPA carRepositoryJPA,
                        RaceRepositoryJPA raceRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
        this.carRepositoryJPA = carRepositoryJPA;
        this.raceRepositoryJPA = raceRepositoryJPA;
    }

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(String... args) {

        carRepositoryJPA.deleteAll();
        raceRepositoryJPA.deleteAll();
        userRepositoryJPA.deleteAll();

        for (int i = 0; i < NUMBER_OF_TEST_ENTITIES; i++) {


            Car honda = new
                    Car("honda-" + i, "blue", 110);
            Car mazda = new Car("mazda-" + i, "white", 180);
            Car ford = new Car("ford-" + i, "blue", 105);
            Car audi = new Car("audi-" + i, "black", 200);

            User user = new User();
            user.setName("user1");
            user.addCar(honda);
            user.addCar(audi);
            userRepositoryJPA.save(user);


            Race race = new Race();
            race.setName("Race-" + i);
            race.setStartDate(LocalDate.now());
            race.addUser(user);

            raceRepositoryJPA.save(race);
        }


    }
}