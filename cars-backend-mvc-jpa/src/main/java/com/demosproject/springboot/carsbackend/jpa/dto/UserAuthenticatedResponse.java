package com.demosproject.springboot.carsbackend.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAuthenticatedResponse {

  private UserDto user;

}
