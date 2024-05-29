package org.example.imports.core.domain;

import org.example.imports.core.infra.Gateway;
import org.springframework.stereotype.Component;

@Component
public class GetDataUseCase {

  private final Gateway gateway;

  GetDataUseCase(Gateway gateway) {
    this.gateway = gateway;
  }

  public String execute() {
    String result = gateway.load();
    return result == null ? "<NO DATA>" : result;
  }

}
