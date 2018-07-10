package com.demosproject.springboot.carsbackend.jpa.controllers;

import com.demosproject.springboot.carsbackend.jpa.domain.Car;
import com.demosproject.springboot.carsbackend.jpa.services.CarServiceJPA;
import java.util.List;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jpa")
public class CarControllerJPA {

    private final CarServiceJPA carServiceJPA;

    public CarControllerJPA(CarServiceJPA carServiceJPA){
        this.carServiceJPA = carServiceJPA;
    }

    @GetMapping(value = "/cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> getCars(){
        return carServiceJPA.getCars();
    }

    @GetMapping(value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Car> getCarById(@PathVariable String id){
        return carServiceJPA.getCarById(Long.parseLong(id));
    }

    @PostMapping(value = "/cars", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveCar(@PathVariable Car car) {
        carServiceJPA.saveCar(car);
    }
}
