package com.template.controller;

import com.template.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.given;

public class StatusControllerTest extends BaseTest {
  @Test
  public void test_foo() {
    assertEquals(1, 1);
  }

  @Test
  public void test_getStatus() {
    RestAssured.port = this.port;
    RestAssured.baseURI = "http://localhost";

    Response response = given().when().get("/status").then().extract().response();
    assertEquals(200, response.statusCode());
    assertEquals("{\"status\":\"OK\"}", response.body().asString());
  }
}
