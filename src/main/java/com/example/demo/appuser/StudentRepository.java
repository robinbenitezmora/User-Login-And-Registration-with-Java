package com.example.demo.appuser;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional(dontRollbackOn = { Exception.class })
public interface StudentRepository {

 Optional<AppUser> findByEmail(String email);
}
