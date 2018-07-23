package com.demosproject.springboot.carsbackend.jpa.spring.security;

public class JWTConstants {
  public static final String SECRET = "Th3S3Cr3tKey";
  public static final long EXPIRATION_TIME = 2592000000L; //30 days
  public static final String TOKEN_PREFIX = "CarsProject ";
  public static final String AUTHORIZATION_HEADER = "Authorization";
}