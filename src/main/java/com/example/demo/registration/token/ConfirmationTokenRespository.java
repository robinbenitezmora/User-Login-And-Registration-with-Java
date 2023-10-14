package com.example.demo.registration.token;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRespository extends JpaRepository<ConfirmationToken, Long> {

 Optional<ConfirmationToken> findByToken(String token);
}
