package com.demosproject.springboot.carsbackend.mvc.services.mongodb;


import com.demosproject.springboot.carsbackend.mvc.domain.model.Car;
import com.demosproject.springboot.carsbackend.mvc.repositories.mongodb.CarRepositoryMongoDB;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Service
public class CarServiceMongoDB {

    private CarRepositoryMongoDB carRepositoryMongoDB;

    @Autowired
    public CarServiceMongoDB(CarRepositoryMongoDB carRepositoryMongoDB){
        this.carRepositoryMongoDB = carRepositoryMongoDB;
    }

    public List<Car> getCars(){
        return this.carRepositoryMongoDB.findAll();
    }

    public Optional<Car> getCarById(Integer id){
        return this.carRepositoryMongoDB.findById(id);
    }

    public void saveCar(Car car){
        this.carRepositoryMongoDB.save(car);
    }

}
