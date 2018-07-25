package com.demosproject.springboot.carsbackend.jpa.spring.security.handler;

import static com.demosproject.springboot.carsbackend.jpa.spring.security.JWTConstants.AUTHORIZATION_HEADER;
import static com.demosproject.springboot.carsbackend.jpa.spring.security.JWTConstants.EXPIRATION_TIME;
import static com.demosproject.springboot.carsbackend.jpa.spring.security.JWTConstants.SECRET;
import static com.demosproject.springboot.carsbackend.jpa.spring.security.JWTConstants.TOKEN_PREFIX;

import com.demosproject.springboot.carsbackend.jpa.domain.model.User;
import com.demosproject.springboot.carsbackend.jpa.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final ModelMapper modelMapper;

  private final ObjectMapper objectMapper;

  @Autowired
  public CustomAuthenticationSuccessHandler(ModelMapper modelMapper, ObjectMapper objectMapper) {
    this.modelMapper = modelMapper;
    this.objectMapper = objectMapper;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {

    String token = Jwts.builder()
        .setSubject(((User) authentication.getPrincipal()).getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
        .compact();

    response.addHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + token);
    response.addHeader("Access-Control-Expose-Headers", "Authorization");
    //response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    response.getWriter().write(getUserDtoJsonFromAuthentication(authentication));
    response.flushBuffer();

    clearAuthenticationAttributes(request);
  }

  private String getUserDtoJsonFromAuthentication(Authentication authentication)
      throws JsonProcessingException {
    User authenticatedUser = (User) authentication.getPrincipal();
    UserDto userDto = modelMapper.map(authenticatedUser, UserDto.class);
    return objectMapper.writeValueAsString(userDto);
  }
}