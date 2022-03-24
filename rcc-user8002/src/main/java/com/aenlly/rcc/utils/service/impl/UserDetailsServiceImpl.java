package com.aenlly.rcc.utils.service.impl;

import com.aenlly.rcc.entity.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Aenlly
 * @create by date 2022/02/16 13:27
 * @projectName RefuseClassificationCultivate
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return new LoginUser(username);
  }
}
