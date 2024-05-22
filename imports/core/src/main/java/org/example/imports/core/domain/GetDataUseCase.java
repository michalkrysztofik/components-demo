package org.example.imports.core.domain;

import org.example.imports.persistence.TextFileGateway;
import org.springframework.stereotype.Component;

@Component
public class GetDataUseCase {

  private final TextFileGateway gateway;

  GetDataUseCase(TextFileGateway gateway) {
    this.gateway = gateway;
  }

  public String execute() {
    String result = gateway.load();
    return result == null ? "<NO DATA>" : result;
  }

}
