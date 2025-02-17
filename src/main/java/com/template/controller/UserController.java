package com.template.controller;

import com.template.entity.User;
import com.template.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  private UserHandler userHandler;

  @GetMapping(path = "/user")
  public HttpEntity getUser() {
    User user = userHandler.getUser();

    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping(path = "/login")
  public HttpEntity<String> login() {
    return new ResponseEntity<>("Successfully logged in!", HttpStatus.OK);
  }

  @Autowired
  public void setUserHandler(UserHandler userHandler) {
    this.userHandler = userHandler;
  }
}
