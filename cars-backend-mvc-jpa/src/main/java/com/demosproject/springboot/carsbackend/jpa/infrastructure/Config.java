package com.demosproject.springboot.carsbackend.jpa.infrastructure;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.modelmapper.AbstractConverter;
import org.modelmapper.AbstractProvider;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

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
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(source, format);
        return localDate;
      }
    };

    modelMapper.createTypeMap(String.class, LocalDate.class);
    modelMapper.addConverter(stringLocalDateConverter);
    modelMapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);

    return modelMapper;
  }
}