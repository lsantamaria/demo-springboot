package com.demosproject.springboot.carsbackend.jpa.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class RaceFullDto extends RaceDto {

  private List<UserDto> users;
}
