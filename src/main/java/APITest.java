import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@Slf4j
public class APITest {

    @BeforeEach
    void setUp() {
        // Set the base URL for your API
        RestAssured.baseURI = "https://api.example.com";
    }

    @Test
    void testGetRequest() {
        // Log before making the GET request
        log.info("Sending GET request to /endpoint");

        String response = given()
                .auth().basic("username", "password") // Basic Authentication
                .header("Custom-Header", "Header-Value") // Custom header
                .when()
                .get("/endpoint")
                .then()
                .statusCode(200)
                .body("key", equalTo("expectedValue"))
                .extract().asString();

        // Log after receiving the response
        log.info("Received GET response:\n{}", response);

        // Capture a screenshot (requires Allure setup for screenshots)
        Allure.addAttachment("GET Response Screenshot", "image/png", "screenshot.png");
    }

    @Test
    void testPostRequest() {
        // Log before making the POST request
        log.info("Sending POST request to /endpoint");

        String requestBody = "{\"key\": \"value\"}";

        String response = given()
                .auth().oauth2("bearerToken") // Bearer Token Authentication
                .header("Custom-Header", "Header-Value") // Custom header
                .body(requestBody)
                .when()
                .post("/endpoint")
                .then()
                .statusCode(201)
                .extract().asString();

        // Log after receiving the response
        log.info("Received POST response:\n{}", response);

        // Capture a screenshot (requires Allure setup for screenshots)
        Allure.addAttachment("POST Response Screenshot", "image/png", "screenshot.png");
    }

    @Test
    void testPutRequest() {
        // Log before making the PUT request
        log.info("Sending PUT request to /endpoint");

        String requestBody = "{\"key\": \"updatedValue\"}";

        String response = given()
                .auth().basic("username", "password") // Basic Authentication
                .header("Custom-Header", "Header-Value") // Custom header
                .body(requestBody)
                .when()
                .put("/endpoint")
                .then()
                .statusCode(200)
                .extract().asString();

        // Log after receiving the response
        log.info("Received PUT response:\n{}", response);

        // Capture a screenshot (requires Allure setup for screenshots)
        Allure.addAttachment("PUT Response Screenshot", "image/png", "screenshot.png");
    }

    @Test
    void testDeleteRequest() {
        // Log before making the DELETE request
        log.info("Sending DELETE request to /endpoint");

        given()
                .auth().basic("username", "password") // Basic Authentication
                .header("Custom-Header", "Header-Value") // Custom header
                .when()
                .delete("/endpoint")
                .then()
                .statusCode(204);

        // Log after successful DELETE request
        log.info("DELETE request completed successfully");
    }
}