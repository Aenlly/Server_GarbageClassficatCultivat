package com.aenlly.rcc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.aenlly.rcc.mapper")
public class RccResource8004Application {

  public static void main(String[] args) {
    SpringApplication.run(RccResource8004Application.class, args);
  }
}
