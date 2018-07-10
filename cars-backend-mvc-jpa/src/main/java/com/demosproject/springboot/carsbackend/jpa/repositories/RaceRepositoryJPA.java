package com.demosproject.springboot.carsbackend.jpa.repositories;


import com.demosproject.springboot.carsbackend.jpa.domain.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepositoryJPA extends JpaRepository<Race, Long> {

}
