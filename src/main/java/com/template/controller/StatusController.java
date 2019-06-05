package com.template.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
  @GetMapping(path = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
  public HttpEntity getStatus() {
    return new ResponseEntity<>("We're up and running!", HttpStatus.OK);
  }
}
