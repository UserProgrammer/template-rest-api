package com.template.security;

import com.template.security.exception.InvalidCredentialsException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Collections;

public class BasicAuthenticationProvider implements AuthenticationProvider {
  private final String VALID_USERNAME = "user";
  private final String VALID_PASSWORD = "pass";

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    System.out.println("BasicAuthenticationProvider");
    String username = authentication.getName();
    String password = (String) authentication.getCredentials();

    if (!username.equals(VALID_USERNAME) || !password.equals(VALID_PASSWORD)) {
      throw new InvalidCredentialsException("Invalid credentials provided.");
    }

    return new UsernamePasswordAuthenticationToken(username, "", Collections.emptyList());
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return UsernamePasswordAuthenticationToken.class.equals(aClass);
  }
}
