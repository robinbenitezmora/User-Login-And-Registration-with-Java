package com.example.demo.appuser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional(dontRollbackOn = Exception.class)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

 Optional<AppUser> findByEmail(String email);
}
