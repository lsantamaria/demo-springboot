package com.demosproject.springboot.carsbackend.jpa.services;


import com.demosproject.springboot.carsbackend.jpa.domain.Race;
import com.demosproject.springboot.carsbackend.jpa.domain.RaceDto;
import com.demosproject.springboot.carsbackend.jpa.domain.RaceFullDto;
import com.demosproject.springboot.carsbackend.jpa.repositories.RaceRepositoryJPA;
import java.util.List;
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
public class RaceServiceJPA {

  private ModelMapper modelMapper;

  private RaceRepositoryJPA raceRepositoryJPA;

  @Autowired
  public RaceServiceJPA(RaceRepositoryJPA raceRepositoryJPA, ModelMapper modelMapper) {
    this.raceRepositoryJPA = raceRepositoryJPA;
    this.modelMapper = modelMapper;
  }

  public List<RaceDto> getRaces() {
    List<Race> races = this.raceRepositoryJPA.findAll();
    return races.stream().map(race -> modelMapper.map(race, RaceDto.class))
        .collect(Collectors.toList());
  }

  public Optional<RaceFullDto> getRaceByID(Long id) {
    Optional<Race> raceOptional = this.raceRepositoryJPA.findById(id);
    return raceOptional.map(race -> modelMapper.map(race, RaceFullDto.class));
  }

  public void saveRace(RaceFullDto raceFullDto) {
    Race race = modelMapper.map(raceFullDto, Race.class);
    this.raceRepositoryJPA.save(race);
  }

}