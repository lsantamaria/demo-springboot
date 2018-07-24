package com.demosproject.springboot.carsbackend.jpa.controllers;

import com.demosproject.springboot.carsbackend.jpa.dto.UserDto;
import com.demosproject.springboot.carsbackend.jpa.domain.services.UserServiceJPA;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for dealing with user entities.
 */
@RestController
@RequiredArgsConstructor
public class UserControllerJPA extends BaseJPAControllers {

  static final String GET_ALL_USERS = BASE_PATH + "/users";

  static final String GET_USER_BY_ID = BASE_PATH + "/users/{id}";

  static final String SAVE_USER = BASE_PATH + "/users";

  private final UserServiceJPA userServiceJPA;

  /**
   * Gets all the users.
   *
   * @return the users list in DTO format.
   */
  @GetMapping(value = GET_ALL_USERS, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<UserDto> getUsers() {
    return userServiceJPA.getUsers();
  }

  /**
   * Gets the user that has the given id.
   *
   * @param id the user id.
   * @return the user DTO.
   */
  @GetMapping(value = GET_USER_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDto getUserById(@PathVariable String id) {
    return userServiceJPA.getUserById(Long.parseLong(id));
  }
}
