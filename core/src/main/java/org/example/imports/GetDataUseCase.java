package org.example.imports;

import org.example.persistance.TextFileGateway;
import org.springframework.stereotype.Component;

@Component
public class GetDataUseCase {

  private final TextFileGateway gateway;

  public GetDataUseCase(TextFileGateway gateway) {
    this.gateway = gateway;
  }

  public String execute() {
    String result = gateway.load();
    return result == null ? "<NO DATA>" : result;
  }

}
