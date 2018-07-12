package com.demosproject.springboot.carsbackend.jpa.controllers;

import com.demosproject.springboot.carsbackend.jpa.dto.UserDto;
import com.demosproject.springboot.carsbackend.jpa.dto.LoginDto;
import com.demosproject.springboot.carsbackend.jpa.services.LoginService;
import javax.security.auth.login.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  private LoginService loginService;

  @Autowired
  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping("/login")
  public UserDto login(@RequestBody LoginDto loginDto) throws LoginException {

    return loginService.login(loginDto.getEmail(), loginDto.getPassword());
  }

}
