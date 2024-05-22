package org.example.imports.api;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.impl.io.DefaultClassicHttpRequestFactory;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.UncheckedIOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = org.example.app.Application.class)
class ImportTest {

  @Value("${local.server.port}")
  private int runningServerPort;

  @Test
  void test() throws InterruptedException {
    startImport("test1", "Test123");
    Thread.sleep(1000);
    sendGetData();
  }

  private void startImport(String login, String password) {
    ClassicHttpRequest request = DefaultClassicHttpRequestFactory.INSTANCE.newHttpRequest(
      "GET",
      "http://localhost:" + runningServerPort + "/start_import?login=" + login + "&password=" + password
    );
    try (CloseableHttpClient client = HttpClients.createDefault()) {
      client.execute(request, response -> {
        System.out.println("\n==========================================");
        System.out.println("START IMPORT:");
        System.out.println("HTTP" + response.getCode());
        System.out.println("==========================================\n");
        assertThat(response.getCode()).isEqualTo(200);
        return null;
      });
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private void sendGetData() {
    ClassicHttpRequest request =
      DefaultClassicHttpRequestFactory.INSTANCE.newHttpRequest("GET", "http://localhost:" + runningServerPort + "/get_data");
    try (CloseableHttpClient client = HttpClients.createDefault()) {
      client.execute(request, response -> {
        System.out.println("\n==========================================");
        System.out.println("GET DATA:");
        System.out.println(EntityUtils.toString(response.getEntity()));
        System.out.println("==========================================\n");
        assertThat(response.getCode()).isEqualTo(200);
        return null;
      });
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

}
