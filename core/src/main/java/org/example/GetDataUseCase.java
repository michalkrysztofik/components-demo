package org.example;

import org.springframework.stereotype.Component;

@Component
public class GetDataUseCase {

  private final Gateway gateway;

  public GetDataUseCase(Gateway gateway) {
    this.gateway = gateway;
  }

  public String execute() {
    String result = gateway.load();
    return result == null ? "<NO DATA>" : result;
  }

}
