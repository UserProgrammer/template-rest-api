package com.template.handler;

import com.template.entity.User;

public class UserHandler {
  public User getUser() {
    User user = new User();
    user.setName("John Doe");

    return user;
  }
}
