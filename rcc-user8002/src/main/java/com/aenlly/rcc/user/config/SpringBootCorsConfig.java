package com.aenlly.rcc.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域请求配置
 *
 * @author Aenlly
 * @create by date 2022/01/14 22:56
 * @projectName RefuseClassificationCultivate
 */
@Configuration
public class SpringBootCorsConfig {

  /**
   * 构建CORS配置容器
   *
   * @return CORS配置容器
   */
  private CorsConfiguration buildCorsConfig() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOrigin("*"); // 允许任意域名访问
    configuration.addAllowedHeader("*"); // 允许任意请求头
    configuration.addAllowedMethod("*"); // 允许任意方法
    return configuration;
  }

  /**
   * 构建CORS拦截器
   *
   * @return CORS拦截器
   */
  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", buildCorsConfig());
    return new CorsFilter(source);
  }
}
