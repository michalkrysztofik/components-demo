package org.example.imports;

import org.example.persistance.TextFileGateway;
import org.springframework.stereotype.Component;

@Component
public class ImportUseCase {

  private final Importer importer;
  private final TextFileGateway gateway;

  public ImportUseCase(Importer importer, TextFileGateway gateway) {
    this.importer = importer;
    this.gateway = gateway;
  }

  public void execute(String login, String password) {
    gateway.save(importer.importData(login, password));
  }

}