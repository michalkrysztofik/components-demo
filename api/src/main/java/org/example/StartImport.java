package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartImport {

  private final ImportUseCase importUseCase;

  public StartImport(ImportUseCase importUseCase) {
    this.importUseCase = importUseCase;
  }

  @GetMapping(value = "/start_import")
  public void startImport() {
    importUseCase.execute();
  }

}