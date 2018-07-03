package com.demosproject.springboot.carsbackend.mvc.controllers.mongodb;

import com.demosproject.springboot.carsbackend.mvc.domain.model.Car;
import com.demosproject.springboot.carsbackend.mvc.services.mongodb.CarServiceMongoDB;
import java.util.List;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cars-mongodb")
public class CarControllerMongoDB {

    private final CarServiceMongoDB carServiceMongoDB;

    public CarControllerMongoDB(CarServiceMongoDB CarServiceMongoDB){
        this.carServiceMongoDB = CarServiceMongoDB;
    }

    @GetMapping(value = "/cars", produces = MediaType .APPLICATION_JSON_VALUE)
    public List<Car> getCars(){
        return carServiceMongoDB.getCars();
    }

    @GetMapping(value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Car> getCarById(@PathVariable String id){
        return carServiceMongoDB.getCarById(Integer.parseInt(id));
    }

    @PostMapping(value = "/cars", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveCar(@PathVariable Car car) {
        carServiceMongoDB.saveCar(car);
    }
}
