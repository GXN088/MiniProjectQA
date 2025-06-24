package api;

import api.model.LoginRequest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LoginAPITest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void testLoginSuccess() {
        LoginRequest login = new LoginRequest("eve.holt@reqres.in", "cityslicka");

        given()
            .log().all()
            .contentType("application/json")
            .body(login)
        .when()
            .post("/api/login")
        .then()
            .log().all()
            .statusCode(200)
            .body("token", notNullValue());
    }

    @Test
    public void testLoginFail() {
        LoginRequest login = new LoginRequest("eve.holt@reqres.in", null);

        given()
            .log().all()
            .contentType("application/json")
            .body(login)
        .when()
            .post("/api/login")
        .then()
            .log().all()
            .statusCode(401)
            .body("error", notNullValue());
    }
}