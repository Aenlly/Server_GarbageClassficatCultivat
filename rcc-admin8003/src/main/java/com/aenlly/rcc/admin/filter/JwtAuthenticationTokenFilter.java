package com.aenlly.rcc.admin.filter;

import cn.hutool.json.JSONUtil;
import com.aenlly.rcc.entity.AdminTable;
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

    if (!StringUtils.hasText(token) || token.equals("null") || !JWTUtil.ValidateToken(token)) {
      filterChain.doFilter(request, response);
      return;
    }

    Claims claims = JWTUtil.parseJWT(token);
    String json = claims.getSubject();
    AdminTable adminTable = JSONUtil.toBean(json, AdminTable.class);
    // 设置权限
    ArrayList<SimpleGrantedAuthority> objects = new ArrayList<>();
    objects.add(new SimpleGrantedAuthority("admin"));

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(adminTable, null, objects);

    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    filterChain.doFilter(request, response);
  }
}
