package org.example.random.api;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.impl.io.DefaultClassicHttpRequestFactory;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.UncheckedIOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RandomTest.Application.class)
class RandomTest {

  @Value("${local.server.port}")
  private int runningServerPort;

  @Test
  void test() {
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
        assertThat(response.getCode()).isEqualTo(200);
        return null;
      });
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class
  }, scanBasePackages = {"org.example.random.api", "org.example.random.core"})
  @ConfigurationPropertiesScan
  static class Application {

    public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
    }

  }

}
