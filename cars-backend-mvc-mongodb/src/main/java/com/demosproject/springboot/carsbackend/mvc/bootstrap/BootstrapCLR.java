package com.demosproject.springboot.carsbackend.mvc.bootstrap;

import com.demosproject.springboot.carsbackend.mvc.domain.model.Car;
import com.demosproject.springboot.carsbackend.mvc.domain.model.Race;
import com.demosproject.springboot.carsbackend.mvc.domain.model.User;
import com.demosproject.springboot.carsbackend.mvc.repositories.mongodb.CarRepositoryMongoDB;
import com.demosproject.springboot.carsbackend.mvc.repositories.mongodb.RaceRepositoryMongoDB;
import com.demosproject.springboot.carsbackend.mvc.repositories.mongodb.UserRepositoryMongoDB;
import java.util.Calendar;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
public class BootstrapCLR implements CommandLineRunner {

    private final UserRepositoryMongoDB userRepositoryMongoDB;
    private final CarRepositoryMongoDB carRepositoryMongoDB;
    private final RaceRepositoryMongoDB raceRepositoryMongoDB;

    public BootstrapCLR(UserRepositoryMongoDB userRepositoryMongoDB, CarRepositoryMongoDB carRepositoryMongoDB, RaceRepositoryMongoDB raceRepositoryMongoDB) {
        this.userRepositoryMongoDB = userRepositoryMongoDB;
        this.carRepositoryMongoDB = carRepositoryMongoDB;
        this.raceRepositoryMongoDB = raceRepositoryMongoDB;
    }

    @Override
    public void run(String... args){

        carRepositoryMongoDB.deleteAll();
        raceRepositoryMongoDB.deleteAll();
        userRepositoryMongoDB.deleteAll();


        Car honda = new Car(null, "honda","blue",75);
        Car mazda = new Car(null,"seat","black",34);

        Car ford = new Car(null,"ford","blue",75);
        Car audi = new Car(null,"audi","black",34);

        carRepositoryMongoDB.save(honda);
        carRepositoryMongoDB.save(mazda);
        carRepositoryMongoDB.save(ford);
        carRepositoryMongoDB.save(audi);

        User marcelus = new User(null, "Marcelus",asList(honda,mazda));
        userRepositoryMongoDB.save(marcelus);

        User albert = new User();
        albert.setId(null);
        albert.setName("albert");
        albert.setCars(asList(ford,audi));
        userRepositoryMongoDB.save(albert);

        Race race = new Race();
        race.setName("CARRERA1");
        race.setStartDate(Calendar.getInstance().getTime());
        race.setUsers(asList(marcelus,albert));

        raceRepositoryMongoDB.save(race);



        List<Race> races = raceRepositoryMongoDB.findAll();


        System.out.println("");


    }
}
