package com.demosproject.springboot.carsbackend.jpa.bootstrap;

import com.demosproject.springboot.carsbackend.jpa.domain.model.Car;
import com.demosproject.springboot.carsbackend.jpa.domain.model.Race;
import com.demosproject.springboot.carsbackend.jpa.domain.model.User;
import com.demosproject.springboot.carsbackend.jpa.repositories.CarRepositoryJPA;
import com.demosproject.springboot.carsbackend.jpa.repositories.RaceRepositoryJPA;
import com.demosproject.springboot.carsbackend.jpa.repositories.UserRepositoryJPA;
import java.util.Calendar;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
public class BootstrapCLR implements CommandLineRunner {

    private final UserRepositoryJPA userRepositoryJPA;
    private final CarRepositoryJPA carRepositoryJPA;
    private final RaceRepositoryJPA raceRepositoryJPA;

    public BootstrapCLR(UserRepositoryJPA userRepositoryJPA, CarRepositoryJPA carRepositoryJPA, RaceRepositoryJPA raceRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
        this.carRepositoryJPA = carRepositoryJPA;
        this.raceRepositoryJPA = raceRepositoryJPA;
    }

    @Override
    public void run(String... args){

        carRepositoryJPA.deleteAll();
        raceRepositoryJPA.deleteAll();
        userRepositoryJPA.deleteAll();


        Car honda = new Car("honda","blue",75);
        Car mazda = new Car("seat","black",34);
        Car ford = new Car("ford","blue",75);
        Car audi = new Car("audi","black",34);

        carRepositoryJPA.save(honda);
        carRepositoryJPA.save(mazda);
        carRepositoryJPA.save(ford);
        carRepositoryJPA.save(audi);

        User marcelus = new User("Marcelus", asList(honda,mazda));
        userRepositoryJPA.save(marcelus);

        User albert = new User();
        albert.setName("albert");
        albert.setCars(asList(ford,audi));
        userRepositoryJPA.save(albert);

        Race race = new Race();
        race.setName("CARRERA1");
        race.setStartDate(Calendar.getInstance().getTime());
        race.setUsers(asList(marcelus,albert));

        raceRepositoryJPA.save(race);



        List<Race> races = raceRepositoryJPA.findAll();


        System.out.println("");


    }
}
