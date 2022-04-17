package com.aenlly.rcc.filter;

import cn.hutool.json.JSONUtil;
import com.aenlly.rcc.entity.LoginUser;
import com.aenlly.rcc.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Aenlly
 * @create by date 2022/02/16 15:38
 * @projectName RefuseClassificationCultivate
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String token = request.getHeader("token");

    if (!StringUtils.hasText(token) || "null".equals(token) || JWTUtil.ValidateToken(token)) {
      filterChain.doFilter(request, response);
      return;
    }

    Claims claims = JWTUtil.parseJWT(token);
    String json = claims.getSubject();
    LoginUser loginUser = JSONUtil.toBean(json, LoginUser.class);

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    filterChain.doFilter(request, response);
  }
}
