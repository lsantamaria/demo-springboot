package com.demosproject.springboot.carsbackendmvc.bootstrap;

import com.demosproject.springboot.carsbackendmvc.domain.model.Car;
import com.demosproject.springboot.carsbackendmvc.domain.model.User;
import com.demosproject.springboot.carsbackendmvc.repositories.CarRepository;
import com.demosproject.springboot.carsbackendmvc.repositories.RaceRepository;
import com.demosproject.springboot.carsbackendmvc.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
public class BootrapCLR implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final RaceRepository raceRepository;

    public BootrapCLR(UserRepository userRepository, CarRepository carRepository, RaceRepository raceRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public void run(String... args){
        Car honda = new Car("honda","blue",null,75);
        Car mazda = new Car("seat","black",null,34);

        carRepository.save(honda);
        carRepository.save(mazda);

        User marcelus = new User("Marcelus",asList(honda,mazda));
        userRepository.save(marcelus);
    }
}
