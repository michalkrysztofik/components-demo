package org.example.imports.core.domain;

import org.example.imports.core.infra.Importer;
import org.example.imports.persistence.TextFileGateway;
import org.springframework.stereotype.Component;

@Component
public class ImportUseCase {

  private final Importer importer;
  private final TextFileGateway gateway;

  ImportUseCase(Importer importer, TextFileGateway gateway) {
    this.importer = importer;
    this.gateway = gateway;
  }

  public void execute(String login, String password) {
    gateway.save(importer.importData(login, password));
  }

}