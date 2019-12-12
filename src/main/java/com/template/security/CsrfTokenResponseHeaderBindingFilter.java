package com.template.security;

import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CsrfTokenResponseHeaderBindingFilter extends OncePerRequestFilter {
  private static final String CSRF_TOKEN_NAME = "x-csrf-token";
  private CsrfTokenRepository csrfTokenRepository;

  public CsrfTokenResponseHeaderBindingFilter(CsrfTokenRepository csrfTokenRepository) {
    this.csrfTokenRepository = csrfTokenRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    // TODO: Since the CustomCsrfAuthenticationStrategy will always override the value of this header, I should be able to get rid of this if statement.
    if (response.getHeader("x-csrf-token") == null) {
      response.addHeader(CSRF_TOKEN_NAME, csrfTokenRepository.loadToken(request).getToken());
    }

    filterChain.doFilter(request, response);
  }
}
