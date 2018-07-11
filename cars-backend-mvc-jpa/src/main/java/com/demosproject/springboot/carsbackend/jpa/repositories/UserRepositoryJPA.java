package com.demosproject.springboot.carsbackend.jpa.repositories;

import com.demosproject.springboot.carsbackend.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJPA extends JpaRepository<User, Long> {

  User findByEmail(String email);

}
