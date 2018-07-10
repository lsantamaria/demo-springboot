package com.demosproject.springboot.carsbackend.jpa.domain.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany
    private List<Car> cars;

    @ManyToMany(mappedBy = "race_id")
    private List<Race> races;

    public User(String name, List<Car> cars) {
        this.name = name;
        this.cars = cars;
    }
}
