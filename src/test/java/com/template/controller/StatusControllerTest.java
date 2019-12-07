package com.template.controller;

import com.template.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

  @Test
  public void test_postStatus() {
    RestAssured.port = this.port;
    RestAssured.baseURI = "http://localhost";

    Response response = given()
        .header("Authorization", "Basic dXNlcjpwYXNz").when()
        .get("/login")
        .then().extract().response();

    String cookie = response.getHeader("Set-Cookie");
    String csrfToken = response.getHeader("x-csrf-token");

    print("test cookie 1: " + cookie);
    print("csrfToken 1: " + csrfToken);

    assertNotNull(cookie);
    assertNotNull(csrfToken);

    print("###############################################");
    print("###############################################");
    print("###############################################");

    Response postResponse = given()
        .header("Cookie", cookie)
        .header("x-csrf-token", csrfToken).when()
        .post("/auth/status")
        .then().extract().response();

    print("test cookie 2: " + postResponse.header("Cookie"));
    print("csrfToken 2: " + csrfToken);

    print(postResponse.statusCode());
    print(postResponse.body().asString());
  }
}
