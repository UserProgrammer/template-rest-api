package com.template.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StatusController {
  @GetMapping(path = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
  public HttpEntity<Map> getStatus() {
    Map<String, String> response = new HashMap<>();
    response.put("status", "OK");

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
