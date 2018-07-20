package com.demosproject.springboot.carsbackend.jpa.bootstrap;

import static java.util.Collections.singletonList;

import com.demosproject.springboot.carsbackend.jpa.domain.Car;
import com.demosproject.springboot.carsbackend.jpa.domain.Race;
import com.demosproject.springboot.carsbackend.jpa.domain.Role;
import com.demosproject.springboot.carsbackend.jpa.domain.User;
import com.demosproject.springboot.carsbackend.jpa.repositories.CarRepositoryJPA;
import com.demosproject.springboot.carsbackend.jpa.repositories.RaceRepositoryJPA;
import com.demosproject.springboot.carsbackend.jpa.repositories.UserRepositoryJPA;
import java.time.LocalDate;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class BootstrapCLR implements CommandLineRunner {

    private static final int NUMBER_OF_TEST_ENTITIES = 1;
    private final UserRepositoryJPA userRepositoryJPA;
    private final CarRepositoryJPA carRepositoryJPA;
    private final RaceRepositoryJPA raceRepositoryJPA;

    @Override
    public void run(String... args) {

        carRepositoryJPA.deleteAll();
        raceRepositoryJPA.deleteAll();
        userRepositoryJPA.deleteAll();

        for (int i = 0; i < NUMBER_OF_TEST_ENTITIES; i++) {

            Car honda = new Car("honda-" + i, "blue", 110);
            Car mazda = new Car("mazda-" + i, "white", 180);
            Car ford = new Car("ford-" + i, "blue", 105);
            Car audi = new Car("audi-" + i, "black", 200);

            Role userRole = new Role("USER");

            User user = new User();
            user.setUsername("Admin-"+i);
            user.setPassword("$2a$10$KCPaOMjqbPx9C4iCpB2dfO025ZMZ1Cim0BvYJTgVXx.JzgQ8ZiwWC");
            user.setName("admin"+i);
            user.addCar(honda);
            user.addCar(audi);
            user.setEnabled(true);
            user.setRoles(new HashSet<>(singletonList(userRole)));
            userRepositoryJPA.save(user);

            Race race = new Race();
            race.setName("Race-" + i);
            race.setStartDate(LocalDate.now());
            race.addUser(user);

            raceRepositoryJPA.save(race);
        }
    }
}