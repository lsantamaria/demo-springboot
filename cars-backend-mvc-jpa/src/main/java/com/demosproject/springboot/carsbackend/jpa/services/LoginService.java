package com.demosproject.springboot.carsbackend.jpa.services;

import com.demosproject.springboot.carsbackend.jpa.domain.User;
import com.demosproject.springboot.carsbackend.jpa.dto.UserDto;
import com.demosproject.springboot.carsbackend.jpa.repositories.UserRepositoryJPA;

import javax.security.auth.login.LoginException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  private UserRepositoryJPA userRepositoryJPA;

  private ModelMapper modelMapper;

  @Autowired
  private LoginService(UserRepositoryJPA userRepositoryJPA, ModelMapper modelMapper) {
    this.userRepositoryJPA = userRepositoryJPA;
    this.modelMapper = modelMapper;
  }


  public UserDto login(String email, String password) throws LoginException {

    User user = userRepositoryJPA.findByEmail(email);

    if (user != null) {
      if (user.getPassword().equals(password)) {
        return modelMapper.map(user, UserDto.class);
      } else {
        throw new LoginException(String.format("Credentials not valid for user %s", email));
      }
    } else {
      User newUser = new User();
      newUser.setName(email);
      newUser.setEmail(email);
      newUser.setPassword(password);

      newUser = userRepositoryJPA.save(newUser);
      return modelMapper.map(newUser, UserDto.class);
    }
  }

}
