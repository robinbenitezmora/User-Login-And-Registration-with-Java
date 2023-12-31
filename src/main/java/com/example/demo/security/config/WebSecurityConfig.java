package com.example.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.appuser.AppUserService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

 private final AppUserService appUserService;
 private final BCryptPasswordEncoder passwordEncoder;

 @Override
 protected void configure(HttpSecurity http) throws Exception {
  http
    .csrf().disable()
    .authorizeRequests()
    .antMatchers("/api/v*/registration/**")
    .permitAll()
    .anyRequest()
    .authenticated().and()
    .formLogin();
 }

 @Override
 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  auth.authenticationProvider(daoAuthenticationProvider());
 }

 @Bean
 public DaoAuthenticationProvider daoAuthenticationProvider() {
  DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
  provider.setPasswordEncoder((PasswordEncoder) BCryptPasswordEncoder());
  provider.setUserDetailsService(appUserService);
  return provider;
 }

 private Object BCryptPasswordEncoder() {
  return null;
 }
}
