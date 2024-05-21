package org.example.api;

import org.example.imports.GetDataUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetData {

  private final GetDataUseCase getDataUseCase;

  public GetData(GetDataUseCase getDataUseCase) {
    this.getDataUseCase = getDataUseCase;
  }

  @GetMapping(value = "/get_data")
  public String getData() {
    return getDataUseCase.execute();
  }

}