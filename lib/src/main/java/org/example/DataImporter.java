package org.example;

import org.springframework.stereotype.Component;

@Component
public class DataImporter implements Importer {

  @Override
  public String importData(String params) {
    return """
      {
        "iban": "PL123456789",
        "name": "Pko",
        "balance": 100.00
      }""";
  }

}