package com.aenlly.rcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/** @author Aenlly */
@SpringBootApplication
@EnableEurekaServer
public class RccEureka8001Application {

  public static void main(String[] args) {
    SpringApplication.run(RccEureka8001Application.class, args);
  }
}
