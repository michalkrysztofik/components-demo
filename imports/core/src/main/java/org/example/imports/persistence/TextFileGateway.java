package org.example.imports.persistence;

import org.example.imports.core.ports.Gateway;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@Component
public class TextFileGateway implements Gateway {

  private static final Path PATH = Path.of(TextFileGateway.class.getResource("/").getPath(), "db.txt");

  @Override
  public synchronized void save(String input) {
    try {
      Files.writeString(PATH, input, UTF_8, CREATE, TRUNCATE_EXISTING);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String load() {
    if (!Files.exists(PATH))
      return null;
    try {
      return Files.readString(PATH);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}