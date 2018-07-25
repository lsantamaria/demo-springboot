package com.demosproject.springboot.carsbackend.jpa.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.demosproject.springboot.carsbackend.jpa")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("http://localhost:4200")
        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
        .allowedHeaders("X-Auth-Token", "Content-Type", "Authorization")
        .exposedHeaders("Authorization")
        .allowCredentials(false)
        .maxAge(4800);
  }
}