package com.aenlly.rcc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** @author Aenlly */
@SpringBootApplication
@MapperScan(basePackages = "com.aenlly.rcc.mapper")
public class RccUser8002Application {

  public static void main(String[] args) {
    SpringApplication.run(RccUser8002Application.class, args);
  }
}
