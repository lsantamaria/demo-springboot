package com.demosproject.springboot.carsbackend.jpa.services;


import com.demosproject.springboot.carsbackend.jpa.domain.Race;
import com.demosproject.springboot.carsbackend.jpa.dto.RaceDto;
import com.demosproject.springboot.carsbackend.jpa.dto.RaceFullDto;
import com.demosproject.springboot.carsbackend.jpa.repositories.RaceRepositoryJPA;
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
public class RaceServiceJPA {

  private ModelMapper modelMapper;

  private RaceRepositoryJPA raceRepositoryJPA;

  @Autowired
  public RaceServiceJPA(RaceRepositoryJPA raceRepositoryJPA, ModelMapper modelMapper) {
    this.raceRepositoryJPA = raceRepositoryJPA;
    this.modelMapper = modelMapper;
  }

  /**
   * Retrieve a list of races from database and return them using {@link RaceDto} representation.
   *
   * @return a list of Races using DTO representation.
   */
  public List<RaceDto> getRaces() {
    List<Race> races = this.raceRepositoryJPA.findAll();
    return races.stream().map(race -> modelMapper.map(race, RaceDto.class))
        .collect(Collectors.toList());
  }

  /**
   * Retrieve a list of races of a given user from database and return them using {@link RaceDto}
   * representation.
   *
   * @return a list of Races using DTO representation.
   */
  public List<RaceDto> getUserRaces(long userId) {
    List<Race> races = this.raceRepositoryJPA.findByUsers_Id(userId);
    return races.stream().map(race -> modelMapper.map(race, RaceDto.class))
        .collect(Collectors.toList());
  }

  /**
   * Retrieve a race with the given id from the database and return it using its DTO
   * representation.
   *
   * @param id the race id.
   * @return the race DTO.
   */
  public RaceFullDto getRaceByID(Long id) {
    Optional<Race> raceOptional = this.raceRepositoryJPA.findById(id);

    if (raceOptional.isPresent()) {
      return modelMapper.map(raceOptional.get(), RaceFullDto.class);
    } else {
      throw new NoSuchElementException(String.format("Race with id %d does not exist", id));
    }
  }

  /**
   * Save a new race.
   *
   * @param raceDto the race DTO.
   */
  public void saveRace(RaceDto raceDto) {
    Race race = modelMapper.map(raceDto, Race.class);
    this.raceRepositoryJPA.save(race);
  }
}