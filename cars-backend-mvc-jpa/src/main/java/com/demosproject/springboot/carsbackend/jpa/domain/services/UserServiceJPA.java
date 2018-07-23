package com.demosproject.springboot.carsbackend.jpa.domain.services;


import com.demosproject.springboot.carsbackend.jpa.domain.model.User;
import com.demosproject.springboot.carsbackend.jpa.dto.UserDto;
import com.demosproject.springboot.carsbackend.jpa.domain.repositories.UserRepositoryJPA;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class UserServiceJPA {

  private final UserRepositoryJPA userRepositoryJPA;

  private final ModelMapper modelMapper;

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

  public void saveUser(UserDto userDto) {
    User user = modelMapper.map(userDto, User.class);
    this.userRepositoryJPA.save(user);
  }

}