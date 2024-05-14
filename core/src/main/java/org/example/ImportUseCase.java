package org.example;

import org.springframework.stereotype.Component;

@Component
public class ImportUseCase {

  private final Importer importer;
  private final Gateway gateway;

  public ImportUseCase(Importer importer, Gateway gateway) {
    this.importer = importer;
    this.gateway = gateway;
  }

  public void execute() {
    gateway.save(importer.importData("params"));
  }

}