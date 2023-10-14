package com.example.demo.registration.token;

import java.time.LocalDateTime;

import com.example.demo.appuser.AppUser;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConfirmationToken {

 @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
 @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
 private Long id;

 @Column(nullable = false)
 private String token;

 @Column(nullable = false)
 private LocalDateTime createdAt;

 @Column(nullable = false)
 private LocalDateTime expiredAt;
 private LocalDateTime confirmedAt;

 @ManyToOne
 @JoinColumn(nullable = false, name = "app_user_id")
 private AppUser appUser;

 public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt,
   LocalDateTime confirmedAt, AppUser appUser) {
  this.token = token;
  this.createdAt = createdAt;
  this.expiredAt = expiredAt;
  this.confirmedAt = confirmedAt;
  this.appUser = appUser;
 }
}
