package com.demosproject.springboot.carsbackend.jpa.dto;

import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
  private long id;
  @NotNull
  @Email
  private String username;

  @NotNull
  private String password;

  @NotNull
  private String name;
  private Set<CarDto> cars;
}