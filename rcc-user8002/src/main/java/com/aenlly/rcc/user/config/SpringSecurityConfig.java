package com.aenlly.rcc.user.config;

import com.aenlly.rcc.user.filter.JwtAuthenticationTokenFilter;
import com.aenlly.rcc.user.utils.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author Aenlly
 * @create by date 2022/02/16 12:37
 * @projectName RefuseClassificationCultivate
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  /** 自定义登录逻辑 */
  @Resource UserDetailsServiceImpl service;

  @Resource JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    http.csrf()
        .disable()
        //    不通过session获取认证容器
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(
            "/carousel-user-view/**",
            "/collect-entity/getCountByDataId",
            "/like-entity/getCountByDataId",
            "/garbage/**",
            "/gift-list-view/**",
            "/gift-type/**",
            "/hot-info-user-view/**",
            "/login",
            "/points/**",
            "/search/searchVoice",
            "/search/searchPicture",
            "/user/getByPoint",
            "/user/getByAnswerPoints",
            "/video-user-view/**",
            "/waste-turn-treasure/getListByTag/**",
            "/waste-turn-treasure/getListSearchByTitle/**",
            "/waste-turn-treasure/getOneById/**",
            "/waste-turn-treasure/upShareCount/**",
            "/waste-turn-treasure/mergeTmpFile")
        .anonymous()
        .antMatchers("/*")
        .hasAnyRole("user")
        .anyRequest()
        .authenticated();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(service).passwordEncoder(getPasswordEncoder());
  }

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
