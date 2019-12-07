package com.template.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

public class CustomBasicAuthenticationFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    BasicAuthenticationToken basicAuthenticationToken = generateBasicAuthenticationToken(request);

    if (basicAuthenticationToken != null) {
      SecurityContextHolder.getContext().setAuthentication(basicAuthenticationToken);
    }

    filterChain.doFilter(request, response);
  }

  private BasicAuthenticationToken generateBasicAuthenticationToken(HttpServletRequest request) {
    System.out.println("generateBasicAuthenticationToken");
    String authorizationHeaderValue = request.getHeader("Authorization");
    BasicAuthenticationToken basicAuthenticationToken = null;

    if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Basic ")) {
      System.out.println("basic authentication header provided");
      final String base64EncodedCredentials = authorizationHeaderValue.replace("Basic ", "").trim();
      final byte[] decodedCredentials = Base64.getDecoder().decode(base64EncodedCredentials);
      final String credentials = new String(decodedCredentials);
      final String[] splitCredentials = credentials.split(":", 2);

      System.out.println("credentials: " + credentials);
      System.out.println("username: " + splitCredentials[0]);
      System.out.println("password: " + splitCredentials[1]);

      basicAuthenticationToken = new BasicAuthenticationToken (splitCredentials[0], splitCredentials[1]);
    }

    return basicAuthenticationToken;
  }
}
