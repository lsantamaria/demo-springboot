package com.demosproject.springboot.carsbackend.jpa.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CarDto {

  private long id;
  @NotNull
  private String brand;
  @NotNull
  private String color;
  @Positive
  @NotNull
  private int power;
}
