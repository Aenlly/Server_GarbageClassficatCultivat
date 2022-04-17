package com.aenlly.rcc.config;

import com.aenlly.rcc.filter.JwtAuthenticationTokenFilter;
import com.aenlly.rcc.handler.ResultAccessDeniedHandler;
import com.aenlly.rcc.handler.ResultAuthenticationEntryPoint;
import com.aenlly.rcc.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;

/**
 * @author Aenlly
 * @create by date 2022/02/16 12:37
 * @projectName RefuseClassificationCultivate
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   自定义登录逻辑
   */
  @Resource
  UserDetailsServiceImpl service;

  @Resource
  JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

  @Resource
  CorsFilter corsFilter;

  @Resource
  ResultAccessDeniedHandler resultAccessDeniedHandler;

  @Resource
  ResultAuthenticationEntryPoint resultAuthenticationEntryPoint;

  /**
   放行静态资源

   @param web
   @throws Exception
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
       .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/swagger-resources/**", "/configuration", "/security", "/swagger-ui.html", "/webjars/**", "/druid/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
    http.csrf()
        .disable()
        //    不通过session获取认证容器
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        //    使登录接口能进行访问,不能写/api/login，因为api是全局index的配置
        .antMatchers("/login", "/swagger-ui/**", "/druid/**")
        .anonymous()
        // 除上面之外的所有请求，都需要进行鉴权
        .antMatchers("/*")
        .hasAnyRole("admin")
        .anyRequest()
        .authenticated();
    http.exceptionHandling()
        .accessDeniedHandler(resultAccessDeniedHandler)
        .authenticationEntryPoint(resultAuthenticationEntryPoint);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(service).passwordEncoder(getPasswordEncoder());
  }

  /**
   * 加密算法
   *
   * @return
   */
  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }
}
