package com.aenlly.rcc.admin.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aenlly
 * @create by date 2021/12/11 19:12
 * @projectName RefuseClassificationCultivate
 */
@Configuration
public class SpringBootConfig {
  @Bean
  // @LoadBalanced,去掉该注解，不走服务注册中心
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  // 加密对象
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }
}