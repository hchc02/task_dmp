package dans.multi.pro.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dans.multi.pro.model.User;

public class UserService implements UserDetails{
  private Long id;

  private String username;

  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserService(Long id2, String username2, String password2, List<?> arrayList) {
  }

  public static UserService build(User user) {
		return new UserService(
				user.getId(), 
				user.getUsername(), 
				user.getPassword(), 
				new ArrayList<>());
	}

  public Long getId() {
		return id;
	}

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    
    return authorities;
  }

  @Override
  public String getPassword() {
    
    return password;
  }

  @Override
  public String getUsername() {
    
    return username;
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
