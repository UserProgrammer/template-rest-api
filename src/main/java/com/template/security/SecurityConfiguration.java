package com.template.security;

import com.google.common.collect.ImmutableList;
import com.template.security.exception.CustomCsrfAuthenticationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();

    httpSecurity
        .authorizeRequests().antMatchers("/status").permitAll()
        .anyRequest().authenticated().and()
        .formLogin().disable()
        .httpBasic().and()
        .cors().and().csrf().csrfTokenRepository(csrfTokenRepository)
        .sessionAuthenticationStrategy(new CustomCsrfAuthenticationStrategy(csrfTokenRepository)).and()
        .authenticationProvider(new BasicAuthenticationProvider())
        .addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(csrfTokenRepository), CsrfFilter.class);
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.setAllowedOrigins(new ArrayList<>());
    configuration.setAllowedMethods(ImmutableList.of("GET", "POST"));
    configuration.setAllowedHeaders(ImmutableList.of("*"));
    configuration.setExposedHeaders(ImmutableList.of("X-Csrf-Token"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}
