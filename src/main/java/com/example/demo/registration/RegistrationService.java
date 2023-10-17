package com.example.demo.registration;

import org.springframework.stereotype.Service;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRole;
import com.example.demo.appuser.AppUserService;
import com.example.demo.registration.token.ConfirmationToken;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {

  private final AppUserService appUserService;
  private EmailValidator emailValidator;

  public ConfirmationToken register(RegistrationRequest request) {
    boolean isValidEmail = emailValidator.test(request.getEmail());
    if (!isValidEmail) {
      throw new IllegalStateException("email not valid");
    }
    return appUserService.signUpUser(new AppUser(request.getFirstName(), request.getLastName(), request.getEmail(),
        request.getPassword(), AppUserRole.USER, isValidEmail, isValidEmail));
  }

  public String confirmToken(String token) {
    return appUserService.confirmToken(token);
  }

}
