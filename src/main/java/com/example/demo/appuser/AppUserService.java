package com.example.demo.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

  private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

  private final AppUserRepository appUserRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final ConfirmationTokenService confirmationTokenService;

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

  public ConfirmationToken signUpUser(AppUser appUser) {
    boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
    if (userExists) {
      throw new IllegalStateException("email already taken");
    }
    String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
    appUser.setPassword(encodedPassword);
    appUserRepository.save(appUser);
    String tokenValue = UUID.randomUUID().toString();
    ConfirmationToken confirmationToken = new ConfirmationToken(tokenValue, LocalDateTime.now(),
        LocalDateTime.now().plusMinutes(15), null, appUser);
    confirmationTokenService.saveConfirmationToken(confirmationToken);
    return confirmationToken;
  }

  public String confirmToken(String token) {
    return null;
  }

  public void enableAppUser(String email) {
  }
}