package org.example.random.api;

import org.example.random.core.RandomNumberGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GetRandom {

  private final RandomNumberGenerator randomNumberGenerator;

  GetRandom(RandomNumberGenerator randomNumberGenerator) {
    this.randomNumberGenerator = randomNumberGenerator;
  }

  @GetMapping(value = "/get_random")
  String getRandom() {
    return randomNumberGenerator.execute();
  }

}