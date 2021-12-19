package com.aenlly.rcc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/** @author Aenlly */
@SpringBootApplication
@MapperScan(basePackages = "com.aenlly.rcc.mapper")
@EnableFeignClients // 开启 Spring Cloud Feign的支持功能，进行远程调用
public class RccUser8002Application {

  public static void main(String[] args) {
    SpringApplication.run(RccUser8002Application.class, args);
  }
}
