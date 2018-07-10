package com.demosproject.springboot.carsbackend.jpa.repositories;


import com.demosproject.springboot.carsbackend.jpa.domain.Race;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepositoryJPA extends JpaRepository<Race, Long> {

  List<Race> findByUsers_Id(long id);
}
