package com.demosproject.springboot.carsbackend.jpa.services;

import com.demosproject.springboot.carsbackend.jpa.domain.Car;
import com.demosproject.springboot.carsbackend.jpa.repositories.CarRepositoryJPA;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Service
public class CarServiceJPA {

    private CarRepositoryJPA carRepositoryJPA;

    @Autowired
    public CarServiceJPA(CarRepositoryJPA CarRepositoryJPA){
        this.carRepositoryJPA = CarRepositoryJPA;
    }

    public List<Car> getCars(){
        return this.carRepositoryJPA.findAll();
    }

    public Optional<Car> getCarById(Long id){
        return this.carRepositoryJPA.findById(id);
    }

    public void saveCar(Car car){
        this.carRepositoryJPA.save(car);
    }

}
