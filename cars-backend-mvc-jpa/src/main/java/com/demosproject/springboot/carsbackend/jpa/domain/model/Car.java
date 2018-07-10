package com.demosproject.springboot.carsbackend.jpa.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.GeneratorStrategy;

@NoArgsConstructor
@Data
@Entity
public class Car {

    @GeneratedValue
    @Id
    private long id;
    private String brand;
    private String color;
    private int power;

    public Car(String brand, String color, int power) {
        this.brand = brand;
        this.color = color;
        this.power = power;
    }
}
