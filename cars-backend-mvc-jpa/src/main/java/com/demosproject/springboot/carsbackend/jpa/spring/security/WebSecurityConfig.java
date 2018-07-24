package com.demosproject.springboot.carsbackend.jpa.spring.security;

import static java.util.Collections.singletonList;

import com.demosproject.springboot.carsbackend.jpa.domain.model.Role;
import com.demosproject.springboot.carsbackend.jpa.domain.model.User;
import com.demosproject.springboot.carsbackend.jpa.domain.repositories.UserRepositoryJPA;
import com.demosproject.springboot.carsbackend.jpa.spring.security.filter.JWTAuthorizationFilter;
import com.demosproject.springboot.carsbackend.jpa.spring.security.handler.CustomAuthenticationFailureHandler;
import com.demosproject.springboot.carsbackend.jpa.spring.security.handler.CustomAuthenticationSuccessHandler;
import java.util.HashSet;
import java.util.Optional;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * This class defines all the configuration for Spring Security module. It switches off the deffault
 * security settings in Spring oob.
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserRepositoryJPA userRepositoryJPA;

  private final DataSource dataSource;

  @Autowired
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .authenticationProvider(authenticationProvider())
        .jdbcAuthentication()
        .dataSource(dataSource)
        .withDefaultSchema()
        .passwordEncoder(passwordEncoder());
  }

  /**
   * Entry point for starting the authentication when {@link org.springframework.security.web.access.ExceptionTranslationFilter}
   * raises an authentication exception.
   *
   * @return the authentication entry point implementation.
   */
  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return new RestAuthenticationEntryPoint();
  }

  /**
   * Custom implementation of {@link UserDetailsService}. It supplies a valid UserDetails instance
   * to the other components of the framework in order to authenticate the user.
   */
  @Override
  protected UserDetailsService userDetailsService() {

    return username -> {
      Optional<User> userOptional = userRepositoryJPA.findByUsername(username);
      if (userOptional.isPresent()) {
        User user = userOptional.get();
        user.setRoles(new HashSet<>(singletonList(new Role("USER"))));
        return userOptional.get();
      } else {
        throw new UsernameNotFoundException(
            String.format("User with username %s does not exist", username));
      }
    };
  }

  /**
   * Custom Authentication Provider. It uses the UserDetailsService bean for retrieving a
   * UserDetails instance and authenticate it through the current {@link AuthenticationManager}.
   * A password encoder bean is supplied for dealing with the user passwords hashes.
   */
  private DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailsService());
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;
  }

  /**
   * Password encoder for hashing user passwords
   *
   * @return an instance of BCryptPasswordEncoder
   */
  private PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(11);
  }

  /**
   * General method for configuring the Spring security context. It is the equivalent of the old
   * security.xml file. It defines the permissions for accessing to all the URLs of the
   * application.
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
        .exceptionHandling()
        .authenticationEntryPoint(authenticationEntryPoint())
        .and()
        .headers().frameOptions().sameOrigin()
        .and()
        .authorizeRequests()
        .antMatchers("/login").permitAll()
        .antMatchers("/register").permitAll()
        .antMatchers("/").permitAll()
        .antMatchers("/h2-console").permitAll()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/jpa/**").authenticated()
        .anyRequest().permitAll()
        .and()
        .formLogin()
        .successHandler(new CustomAuthenticationSuccessHandler())
        .failureHandler(new CustomAuthenticationFailureHandler())
        .passwordParameter("password")
        .usernameParameter("email")
        .and()
        .addFilter(new JWTAuthorizationFilter(super.authenticationManagerBean()))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf().disable()
        .cors()
        .and()
        .logout().permitAll();
  }
}
