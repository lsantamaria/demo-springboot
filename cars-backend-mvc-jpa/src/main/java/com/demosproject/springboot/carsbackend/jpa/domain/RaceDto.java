package com.demosproject.springboot.carsbackend.jpa.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceDto {

  private long id;
  private String name;
  private Date startDate;

}