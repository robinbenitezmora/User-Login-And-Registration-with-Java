package com.example.demo.email;

import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

 private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(EmailService.class);

 private final JavaMailSender mailSender;

 @Override
 @Async
 public void send(String to, String email) {
  try {
   MimeMessage message = mailSender.createMimeMessage();
   MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
   helper.setText(email, true);
   helper.setTo(to);
   helper.setSubject("Confirm your email");
   helper.setFrom("Hello to everyone");

  } catch (MessagingException e) {
   LOGGER.error("failed to send email", e);
   throw new IllegalStateException("failed to send email");
  }
 }

}
