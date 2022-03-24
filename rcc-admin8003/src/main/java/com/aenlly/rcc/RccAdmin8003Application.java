package com.aenlly.rcc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "com.aenlly.rcc.mapper")
@EnableSwagger2
@EnableFeignClients // 开启 Spring Cloud Feign的支持功能，进行远程调用
public class RccAdmin8003Application {

  public static void main(String[] args) {
    SpringApplication.run(RccAdmin8003Application.class, args);
  }
}
