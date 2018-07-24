package com.demosproject.springboot.carsbackend.jpa.domain.model;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@AllArgsConstructor
@Table(name = "USER")
public class User implements Serializable, UserDetails{

  public User(){
  }

  public User(String name, Set<Car> cars) {
    this.name = name;
    this.cars = cars;
  }

  @Id
  @GeneratedValue
  private long id;

  @Column(nullable = false, unique = true, name = "username")
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;

  @Column(name = "account_expired" , columnDefinition = "boolean default 0")
  private boolean accountExpired;

  @Column(name = "account_enabled", columnDefinition = "boolean default 0")
  private boolean enabled;

  @Column(name = "account_locked", columnDefinition = "boolean default 0")
  private boolean accountLocked;

  @Column(name = "credentials_expired", columnDefinition = "boolean default 0")
  private boolean credentialsExpired;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Car> cars = new HashSet<>();

  @ManyToMany(mappedBy = "", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<Race> races = new HashSet<>();

  public void addCar(Car car) {
    cars.add(car);
    car.setUser(this);
  }

  public void addRole(Role role){
    roles.add(role);
  }

  public void removeCar(Car car) {
    car.setUser(null);
    cars.remove(car);
  }

  public void removeRole(Role role){
    roles.remove(role);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(toList());
  }

  public boolean isAccountNonLocked() {
    return !accountLocked;
  }

  public boolean isCredentialsNonExpired() {
    return !credentialsExpired;
  }

  public boolean isAccountNonExpired() {
    return !accountExpired;
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
    return getId() == user.getId() &&
        Objects.equals(getUsername(), user.getUsername()) &&
        Objects.equals(getPassword(), user.getPassword()) &&
        Objects.equals(getName(), user.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getUsername(), getPassword(), getName());
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}