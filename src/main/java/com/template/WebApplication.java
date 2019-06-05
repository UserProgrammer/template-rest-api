package com.template;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:controllers.xml"})
public class WebApplication{

  public static void main(String[] args){
    SpringApplication.run(WebApplication.class, args);
  }
}
