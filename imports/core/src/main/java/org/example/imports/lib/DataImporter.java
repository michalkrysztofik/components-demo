package org.example.imports.lib;

import org.example.imports.core.ports.Importer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class DataImporter implements Importer {

  private final HttpClient httpClient;

  DataImporter() {
    this.httpClient = HttpClient.newHttpClient();
  }

  @Override
  public String importData(String login, String password) {
    try {
      String authCode = signIn(login, password);
      return fetchAccount(authCode);
    } catch (IOException | InterruptedException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  private String signIn(String login, String password) throws IOException, InterruptedException, URISyntaxException {
    String form = Map.of("username", login,
        "password", password,
        "sms_otp", password,
        "country", "pl"
      ).entrySet().stream()
      .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), UTF_8))
      .collect(Collectors.joining("&"));
    HttpRequest request = HttpRequest.newBuilder()
      .uri(new URI("https://bank.kontomatik.com/scraper/login"))
      .header("Content-Type", "application/x-www-form-urlencoded")
      .POST(HttpRequest.BodyPublishers.ofString(form))
      .build();
    return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body().substring(13, 45);
  }

  private String fetchAccount(String apiCode) throws URISyntaxException, IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
      .uri(new URI("https://bank.kontomatik.com/api/accounts"))
      .GET()
      .header("Content-Type", "application/json")
      .header("authorization", "Bearer " + apiCode)
      .build();
    return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
  }

}