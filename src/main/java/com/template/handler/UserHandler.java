package com.template.handler;

import com.template.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserHandler {
  public User getUser() {
    User user = new User();
    user.setName("John Doe");

    return user;
  }
}
