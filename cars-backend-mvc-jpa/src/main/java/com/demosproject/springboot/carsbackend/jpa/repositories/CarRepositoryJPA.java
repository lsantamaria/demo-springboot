package com.demosproject.springboot.carsbackend.jpa.repositories;

import com.demosproject.springboot.carsbackend.jpa.domain.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepositoryJPA extends JpaRepository<Car, Long> {

  List<Car> findByUser_Id(Long userId);
}
