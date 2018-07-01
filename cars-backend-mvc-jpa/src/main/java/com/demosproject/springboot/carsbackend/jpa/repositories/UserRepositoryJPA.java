package com.demosproject.springboot.carsbackend.jpa.repositories;


import com.demosproject.springboot.carsbackend.jpa.domain.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJPA extends JpaRepository<User,Integer> {

    List<User> findByName(String name);



}
