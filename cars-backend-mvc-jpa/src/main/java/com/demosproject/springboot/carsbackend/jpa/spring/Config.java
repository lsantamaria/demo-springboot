package com.demosproject.springboot.carsbackend.jpa.spring;

import com.demosproject.springboot.carsbackend.jpa.domain.model.Race;
import com.demosproject.springboot.carsbackend.jpa.domain.model.User;
import com.demosproject.springboot.carsbackend.jpa.dto.RaceDto;
import com.demosproject.springboot.carsbackend.jpa.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class Config {

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurerAdapter() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:4200");
      }
    };
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder(){
    return new BCryptPasswordEncoder();
  }

  private static final String DATE_TIME_PATTERN = "yyyy-MM-dd";

  @Bean
  public ObjectMapper objectMapper(){
    return new ObjectMapper();
  }

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
        return source.stream().map(User::getId)
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

    Converter<String,String> hashPasswordConverter = new AbstractConverter<String, String>() {
      @Override
      protected String convert(String source) {
        return bCryptPasswordEncoder().encode(source);
      }
    };

    Converter<String,String> stringToStringConverter = new AbstractConverter<String, String>() {
      @Override
      protected String convert(String source) {
        return source;
      }
    };

    //Hash password before storing user to database
    //Change frontend email param to username
    modelMapper.createTypeMap(UserDto.class, User.class)
        .addMappings(
            mapper -> mapper.using(hashPasswordConverter)
            .map(UserDto::getPassword, User::setPassword))
        .addMappings(
            mapper -> mapper.map(UserDto::getEmail, User::setUsername)
        )
    ;

    modelMapper.createTypeMap(User.class, UserDto.class)
        .addMappings(
            mapper -> mapper.using(stringToStringConverter)
            .map(User::getUsername, UserDto::setEmail))
        //Prevent the password to be returned to the client
        .addMappings(mapper -> mapper.skip(UserDto::setPassword));

    return modelMapper;
  }
}