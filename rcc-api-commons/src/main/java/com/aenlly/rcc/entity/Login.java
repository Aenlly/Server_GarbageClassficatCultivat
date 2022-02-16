package com.aenlly.rcc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Aenlly
 * @create by date 2022/02/16 16:10
 * @projectName RefuseClassificationCultivate
 */
@Data
@AllArgsConstructor
public class Login implements UserDetails {

  private static final long serialVersionUID = -3209442352224035346L;
  private AdminTable adminTable;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
  }

  @Override
  public String getPassword() {
    return adminTable.getPassword();
  }

  @Override
  public String getUsername() {
    if (StringUtils.isNotBlank(adminTable.getTel())) {
      return adminTable.getTel();
    } else {
      return adminTable.getEmail();
    }
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
}
