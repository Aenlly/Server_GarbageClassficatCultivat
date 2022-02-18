package com.aenlly.rcc.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

/**
 * @author Aenlly
 * @create by date 2022/02/16 16:10
 * @projectName RefuseClassificationCultivate
 */
@Data
@Slf4j
public class LoginUser implements UserDetails {

  private static final long serialVersionUID = -3209442352224035346L;
  private String userId;

  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return AuthorityUtils.commaSeparatedStringToAuthorityList("user");
  }

  @Override
  public String getPassword() {
    return passwordEncoder.encode(userId);
  }

  @Override
  public String getUsername() {
    return userId;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public LoginUser(String userId) {
    this.userId = userId;
  }
}
