package com.template.security.exception;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomCsrfAuthenticationStrategy implements SessionAuthenticationStrategy {
  private final CsrfTokenRepository csrfTokenRepository;

  public CustomCsrfAuthenticationStrategy(CsrfTokenRepository csrfTokenRepository) {
    this.csrfTokenRepository = csrfTokenRepository;
  }

  @Override
  public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws SessionAuthenticationException {
    boolean containsToken = this.csrfTokenRepository.loadToken(request) != null;
    if (containsToken) {
      // Clear out the existing csrf token.
      this.csrfTokenRepository.saveToken((CsrfToken)null, request, response);
      CsrfToken newToken = this.csrfTokenRepository.generateToken(request);

      // Generate a new csrf token.
      this.csrfTokenRepository.saveToken(newToken, request, response);
      request.setAttribute(CsrfToken.class.getName(), newToken);
      request.setAttribute(newToken.getParameterName(), newToken);
      System.out.println("storing new csrf token in response headers...");
      response.setHeader("x-csrf-token", csrfTokenRepository.loadToken(request).getToken());
    }
  }
}
