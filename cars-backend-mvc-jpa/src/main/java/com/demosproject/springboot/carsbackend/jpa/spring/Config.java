package com.demosproject.springboot.carsbackend.jpa.spring;

import com.demosproject.springboot.carsbackend.jpa.domain.Race;
import com.demosproject.springboot.carsbackend.jpa.domain.User;
import com.demosproject.springboot.carsbackend.jpa.dto.RaceDto;
import com.demosproject.springboot.carsbackend.jpa.dto.UserId;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.AbstractConverter;
import org.modelmapper.AbstractProvider;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
public class Config {
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder(){
    return new BCryptPasswordEncoder();
  }

  private static final String DATE_TIME_PATTERN = "yyyy-MM-dd";

  /**
   * Configuring a modelMapper bean by providing converters needed for the conversions between
   * entities and DTOs.
   *
   * @return a modelMapper instance.
   */
  @Bean
  public ModelMapper modelMapper() {

    ModelMapper modelMapper = new ModelMapper();

    Provider<LocalDate> localDateProvider = new AbstractProvider<LocalDate>() {
      @Override
      public LocalDate get() {
        return LocalDate.now();
      }
    };
    Converter<String, LocalDate> stringLocalDateConverter = new AbstractConverter<String, LocalDate>() {
      @Override
      protected LocalDate convert(String source) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        return LocalDate.parse(source, format);
      }
    };
    Converter<Set<User>, Set<Long>> userUserIdConverter = new AbstractConverter<Set<User>, Set<Long>>() {
      @Override
      protected Set<Long> convert(Set<User> source) {
        return source.stream().map(user -> user.getId())
            .collect(Collectors.toSet());
      }
    };

    modelMapper.createTypeMap(String.class, LocalDate.class);
    modelMapper.addConverter(stringLocalDateConverter);
    modelMapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);

    modelMapper.createTypeMap(Race.class, RaceDto.class)
        .addMappings(
            mapper -> mapper.using(userUserIdConverter)
                .map(Race::getUsers, RaceDto::setUserIds));

    return modelMapper;
  }
}