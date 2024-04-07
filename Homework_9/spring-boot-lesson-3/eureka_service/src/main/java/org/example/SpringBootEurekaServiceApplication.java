package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

@EnableEurekaServer
@SpringBootApplication
public class SpringBootEurekaServiceApplication {
  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SpringBootEurekaServiceApplication.class, args);
  }
}