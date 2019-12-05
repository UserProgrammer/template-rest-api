package com.template.controller;

import com.template.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.given;

public class UserControllerTest extends BaseTest {
  @Test
  public void test_getUser() {
    RestAssured.port = this.port;
    RestAssured.baseURI = "http://localhost";

    Response response = given().when().get("/user").then().extract().response();

    assertEquals(200, response.statusCode());
    assertEquals("{\"name\":\"John Doe\"}", response.body().asString());
  }
}
