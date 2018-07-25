package com.demosproject.springboot.carsbackend.jpa.controllers;

import com.demosproject.springboot.carsbackend.jpa.domain.services.UserServiceJPA;
import com.demosproject.springboot.carsbackend.jpa.dto.UserDto;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

  private final UserServiceJPA userServiceJPA;

  /**
   * Saves a new user. REGISTER endpoint
   *
   * @param userDto the user DTO.
   */
  @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
  public UserDto registerUser(@RequestBody @Validated final UserDto userDto, final
  HttpServletRequest request) throws ServletException {
    UserDto savedUserDto = userServiceJPA.saveUser(userDto);
    request.login(userDto.getEmail(), userDto.getPassword());
    return savedUserDto;
  }
}
