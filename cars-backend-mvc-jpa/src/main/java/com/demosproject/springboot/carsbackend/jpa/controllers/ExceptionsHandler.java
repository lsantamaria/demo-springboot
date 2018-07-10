package com.demosproject.springboot.carsbackend.jpa.controllers;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class for exposing the exception handlers.
 */
public class ExceptionsHandler {

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  private String notFound(Exception e) {
    return e.getMessage();
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  private String badRequest(Exception e) {
    return e.getMessage();
  }
}