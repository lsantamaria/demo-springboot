package com.demosproject.springboot.carsbackend.jpa.repositories;

import com.demosproject.springboot.carsbackend.jpa.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
  Role findByRole(String role);
}
