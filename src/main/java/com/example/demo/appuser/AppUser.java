package com.example.demo.appuser;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity

public class AppUser implements UserDetails {

  @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  @Enumerated(EnumType.STRING)
  private AppUserRole appUserRole;
  private Boolean locked = false;
  private Boolean enabled = false;

  public AppUser(String name, String username, String email, String password, AppUserRole appUserRole, Boolean locked,
      Boolean enabled) {
    this.firstName = name;
    this.lastName = username;
    this.email = email;
    this.password = password;
    this.appUserRole = appUserRole;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
    return Collections.singletonList(authority);
  }

  @Override
  public String getPassword() {
    return password;
  }

  public String getFirstName() {
    return firstName;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override

  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
