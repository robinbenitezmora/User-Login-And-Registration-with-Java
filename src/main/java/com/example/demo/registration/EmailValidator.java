package com.example.demo.registration;

import jakarta.persistence.criteria.Predicate;

public class EmailValidator implements Predicate {
 @Override
 public boolean test(String email) {
  // TODO Auto-generated method stub
  return true;
 }
}