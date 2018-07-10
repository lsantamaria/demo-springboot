package com.demosproject.springboot.carsbackend.jpa.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Car implements Serializable {

  @GeneratedValue
  @Id
  private long id;

  @Column
  private String brand;

  @Column
  private String color;

  @Column
  private int power;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Car(String brand, String color, int power) {
    this.brand = brand;
    this.color = color;
    this.power = power;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Car car = (Car) o;
    return id == car.id &&
        power == car.power &&
        Objects.equals(brand, car.brand) &&
        Objects.equals(color, car.color) &&
        Objects.equals(user, car.user);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, brand, color, power, user);
  }
}