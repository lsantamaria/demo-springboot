package com.demosproject.springboot.carsbackend.mvc.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class User {

    private String id;
    private String name;

    @DBRef(lazy = true)
    private List<Car> cars;

}
