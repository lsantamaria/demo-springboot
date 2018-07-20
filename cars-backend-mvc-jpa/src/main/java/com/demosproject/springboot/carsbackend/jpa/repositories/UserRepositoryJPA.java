package com.demosproject.springboot.carsbackend.jpa.repositories;

import com.demosproject.springboot.carsbackend.jpa.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJPA extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

}
