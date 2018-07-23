package com.demosproject.springboot.carsbackend.jpa.domain.model;

import com.demosproject.springboot.carsbackend.jpa.domain.repositories.converter.LocalDateAttributeConverter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RACE")
public class Race implements Serializable {

  @Id
  @GeneratedValue
  private long id;

  @Column
  private String name;

  @Column
  @Convert(converter = LocalDateAttributeConverter.class)
  private LocalDate startDate;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RACE_USER",
      joinColumns = @JoinColumn(name = "race_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
  )
  private Set<User> users = new HashSet<>();

  public void addUser(User user) {
    users.add(user);
    user.getRaces().add(this);
  }

  public void removeUser(User user) {
    users.remove(user);
    user.getRaces().remove(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Race race = (Race) o;
    return id == race.id &&
        Objects.equals(users, race.users) &&
        Objects.equals(name, race.name) &&
        Objects.equals(startDate, race.startDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, startDate);
  }

  @Override
  public String toString() {
    return "Race{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", startDate=" + startDate +
        '}';
  }
}