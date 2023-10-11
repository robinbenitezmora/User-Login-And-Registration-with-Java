package com.example.demo.appuser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

  private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

  private final AppUserRepository appUserRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Object appUsers = appUserRepository.findByEmail(email);
    AppUser appUser = null;
    if (!((String) appUsers).isEmpty()) {
      appUser = (AppUser) ((Object) appUsers);
    }
    if (appUser == null) {
      throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
    }
    return appUser;
  }
}