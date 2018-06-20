package com.demosproject.springboot.carsbackendmvc.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Car {

    private String brand;
    private String color;
    private String id;
    private int power;


}
