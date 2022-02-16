package com.aenlly.rcc.filter;

import com.aenlly.rcc.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

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
    String token = request.getHeader("Authorization");

    if (!StringUtils.hasText(token) || token.equals("null")) {
      filterChain.doFilter(request, response);
      return;
    }

    Claims claims = JWTUtil.parseJWT(token);
    String user_id = claims.getSubject();
    // 设置权限
    ArrayList<SimpleGrantedAuthority> objects = new ArrayList<>();
    objects.add(new SimpleGrantedAuthority("user"));

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(user_id, null, objects);

    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    filterChain.doFilter(request, response);
  }
}
