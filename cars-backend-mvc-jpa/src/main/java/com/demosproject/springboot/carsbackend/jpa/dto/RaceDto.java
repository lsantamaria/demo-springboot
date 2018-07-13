package com.demosproject.springboot.carsbackend.jpa.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Race DTO without related
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceDto {

  private long id;
  @NotNull
  private String name;
  @Future
  @NotNull
  private String startDate;
  private Set<UserId> userIds;
}