package org.example.api;

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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RandomTest {

  @Value("${local.server.port}")
  public int runningServerPort;

  @Test
  public void test() {
    ClassicHttpRequest request = DefaultClassicHttpRequestFactory.INSTANCE.newHttpRequest(
      "GET",
      "http://localhost:" + runningServerPort + "/get_random"
    );
    try (CloseableHttpClient client = HttpClients.createDefault()) {
      client.execute(request, response -> {
        System.out.println("\n==========================================");
        System.out.println("Random number:");
        System.out.println(EntityUtils.toString(response.getEntity()));
        System.out.println("==========================================\n");
        return null;
      });
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

}
