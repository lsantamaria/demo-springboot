package com.demosproject.springboot.carsbackend.jpa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "USER")
public class User implements Serializable {

  @Id
  @GeneratedValue
  private long id;
  private String name;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Car> cars = new HashSet<>();

  @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Race> races = new HashSet<>();

  public User(String name, Set<Car> cars) {
    this.name = name;
    this.cars = cars;
  }

  public void addCar(Car car) {
    cars.add(car);
    car.setUser(this);
  }

  public void removeCar(Car car) {
    cars.remove(car);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return id == user.id &&
        Objects.equals(name, user.name) &&
        Objects.equals(cars, user.cars) &&
        Objects.equals(races, user.races);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}