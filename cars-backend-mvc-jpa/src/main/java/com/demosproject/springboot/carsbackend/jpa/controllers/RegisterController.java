package com.demosproject.springboot.carsbackend.jpa.controllers;

import com.demosproject.springboot.carsbackend.jpa.domain.services.UserServiceJPA;
import com.demosproject.springboot.carsbackend.jpa.dto.UserDto;
import com.demosproject.springboot.carsbackend.jpa.spring.security.handler.CustomAuthenticationSuccessHandler;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

  private final UserServiceJPA userServiceJPA;

  private final AuthenticationProvider authenticationProvider;

  private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

  /**
   * Saves a new user. REGISTER endpoint
   *
   * @param userDto the user DTO.
   */
  @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void registerUser(@RequestBody @Validated final UserDto userDto, final
  HttpServletRequest request, final HttpServletResponse response) throws IOException {
    userServiceJPA.saveUser(userDto);
    loginUser(userDto, request, response);
  }

  private void loginUser(UserDto userDto, HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    Authentication auth = new UsernamePasswordAuthenticationToken(userDto.getEmail(),
        userDto.getPassword());
    auth = authenticationProvider.authenticate(auth);
    customAuthenticationSuccessHandler.onAuthenticationSuccess(request, response, auth);
  }
}
