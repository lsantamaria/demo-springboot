package com.demosproject.springboot.carsbackendmvc.services;


import com.demosproject.springboot.carsbackendmvc.domain.model.Car;
import com.demosproject.springboot.carsbackendmvc.repositories.CarRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public List<Car> getCars(){
        return this.carRepository.findAll();
    }

    public Optional<Car> getCarById(Integer id){
        return this.carRepository.findById(id);
    }

    public void saveCar(Car car){
        this.carRepository.save(car);
    }

}
