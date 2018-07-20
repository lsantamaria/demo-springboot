package com.demosproject.springboot.carsbackend.jpa.spring.security.handler;

import static com.demosproject.springboot.carsbackend.jpa.spring.security.JWTConstants.AUTHORIZATION_HEADER;
import static com.demosproject.springboot.carsbackend.jpa.spring.security.JWTConstants.EXPIRATION_TIME;
import static com.demosproject.springboot.carsbackend.jpa.spring.security.JWTConstants.SECRET;
import static com.demosproject.springboot.carsbackend.jpa.spring.security.JWTConstants.TOKEN_PREFIX;

import com.demosproject.springboot.carsbackend.jpa.domain.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import java.util.Date;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {

    String token = Jwts.builder()
        .setSubject(((User) authentication.getPrincipal()).getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
        .compact();

    response.addHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + token);

    clearAuthenticationAttributes(request);

  }
}