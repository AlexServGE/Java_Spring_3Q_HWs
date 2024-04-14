package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootBookServiceApplication {
  public static void main(String[] args) {
      ConfigurableApplicationContext context = SpringApplication.run(SpringBootBookServiceApplication.class, args);
  }
}
