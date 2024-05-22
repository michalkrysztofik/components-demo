package org.example.imports.api;

import org.example.imports.core.domain.ImportUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class StartImport {

  private final ImportUseCase importUseCase;

  StartImport(ImportUseCase importUseCase) {
    this.importUseCase = importUseCase;
  }

  @GetMapping(value = "/start_import")
  void startImport(@RequestParam String login, @RequestParam String password) {
    importUseCase.execute(login, password);
  }

}