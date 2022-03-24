package com.aenlly.rcc.handler;

import com.aenlly.rcc.utils.ResultUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 未登录或token失效时
 *
 * @author Aenlly
 * @create by date 2022/02/18 17:26
 * @projectName RefuseClassificationCultivate
 */
@Slf4j
@Component
public class ResultAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      AccessDeniedException e)
      throws IOException, ServletException {
    httpServletResponse.setCharacterEncoding("UTF-8");
    httpServletResponse.setContentType("application/json");
    PrintWriter writer = httpServletResponse.getWriter();
    writer.write(new ObjectMapper().writeValueAsString(ResultUtil.resultPermission_Not()));
    writer.flush();
    writer.close();
  }
}
