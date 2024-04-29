package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootIssueServiceApplication {
  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SpringBootIssueServiceApplication.class, args);
  }
}