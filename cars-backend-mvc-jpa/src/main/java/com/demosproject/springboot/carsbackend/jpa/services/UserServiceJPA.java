package com.demosproject.springboot.carsbackend.jpa.services;


import com.demosproject.springboot.carsbackend.jpa.domain.User;
import com.demosproject.springboot.carsbackend.jpa.dto.UserDto;
import com.demosproject.springboot.carsbackend.jpa.repositories.UserRepositoryJPA;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Service
public class UserServiceJPA {

  private UserRepositoryJPA userRepositoryJPA;

  private ModelMapper modelMapper;

  @Autowired
  public UserServiceJPA(UserRepositoryJPA UserRepositoryJPA, ModelMapper modelMapper) {
    this.userRepositoryJPA = UserRepositoryJPA;
    this.modelMapper = modelMapper;
  }

  /**
   * Retrieve a list of user DTOs.
   *
   * @return the user DTOs list.
   */
  public List<UserDto> getUsers() {
    List<User> users = this.userRepositoryJPA.findAll();
    return users.stream().map(user -> modelMapper.map(user, UserDto.class))
        .collect(Collectors.toList());
  }

  /**
   * Retrieve a user by providing its identifier
   *
   * @param id the identifier of the user to retrieve.
   * @return the user.
   */
  public UserDto getUserById(Long id) {
    Optional<User> optionalUser = this.userRepositoryJPA.findById(id);

    if (!optionalUser.isPresent()) {
      throw new NoSuchElementException(String.format("User %d does not exist", id));
    }
    return modelMapper.map(optionalUser.get(), UserDto.class);
  }

  public void saveUser(User car) {
    this.userRepositoryJPA.save(car);
  }

}