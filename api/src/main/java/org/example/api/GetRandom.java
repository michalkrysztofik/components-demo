package org.example.api;

import org.example.random.RandomNumberGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetRandom {

  private final RandomNumberGenerator randomNumberGenerator;

  public GetRandom(RandomNumberGenerator randomNumberGenerator) {
    this.randomNumberGenerator = randomNumberGenerator;
  }

  @GetMapping(value = "/get_random")
  public String getRandom() {
    return randomNumberGenerator.execute();
  }

}