package com.demosproject.springboot.carsbackendmvc.controllers;

import com.demosproject.springboot.carsbackendmvc.domain.model.Car;
import com.demosproject.springboot.carsbackendmvc.domain.model.Race;
import com.demosproject.springboot.carsbackendmvc.services.CarService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService CarService){
        this.carService = CarService;
    }

    @GetMapping(value = "/cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> getRaces(){
        return carService.getCars();
    }

    @GetMapping(value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Car> getRaceById(@PathVariable String id){
        return carService.getCarById(Integer.parseInt(id));
    }

    @PostMapping(value = "/cars", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveRace(@PathVariable Car car) {
        carService.saveCar(car);
    }
}
