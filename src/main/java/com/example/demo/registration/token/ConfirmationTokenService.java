package com.example.demo.registration.token;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

 private final ConfirmationTokenRespository confirmationTokenRespository;

 public void saveConfirmationToken(ConfirmationToken token) {
  confirmationTokenRespository.save(token);
 }

 public void setConfirmedAt(String token) {
 }

 public Object getToken(String token) {
  return null;
 }

}
