package org.example.random;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberGenerator {

  public String execute() {
    return String.valueOf(new Random().nextLong());
  }

}