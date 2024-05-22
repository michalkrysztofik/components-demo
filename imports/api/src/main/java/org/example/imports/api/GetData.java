package org.example.imports.api;

import org.example.imports.core.domain.GetDataUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GetData {

  private final GetDataUseCase getDataUseCase;

  GetData(GetDataUseCase getDataUseCase) {
    this.getDataUseCase = getDataUseCase;
  }

  @GetMapping(value = "/get_data")
  String getData() {
    return getDataUseCase.execute();
  }

}