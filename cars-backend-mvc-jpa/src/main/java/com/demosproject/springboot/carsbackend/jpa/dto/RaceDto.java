package com.demosproject.springboot.carsbackend.jpa.dto;

import java.util.Set;
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

  @NotNull
  private String startDate;
  private Set<Long> userIds;
}