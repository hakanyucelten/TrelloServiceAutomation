package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ApiStepDefinitions {

    private Response response;

    @Then("a valid API endpoint")
    public void setBaseUri() {

        // Make a GET request to the /tokens/{token} endpoint
        Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .queryParams("name", "hakan board2")
                .queryParams("key", "40de027b492cbb68c2f0394afcf1e6e0")
                .queryParams("token", "ATTA4064e5e1b72c92bef8815ae0aa41f3f39310b00e36d19ab3fc413fcf4c0a26a6A77A1CE8")
                .post("https://api.trello.com/1/boards/")
                .then()
                .extract().response();

        // Validate the status code
        assertEquals(response.getStatusCode(), 200, "Unexpected status code");

        // Print the response body
        System.out.println("Response Body:\n" + response.getBody().asString());

    }

    @Then("create a card")
    public void createCard() {
        // Make a GET request to the /tokens/{token} endpoint
        Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .queryParams("name", "beymen")
                .queryParams("idList", "v94Lm2lg")
                .queryParams("key", "40de027b492cbb68c2f0394afcf1e6e0")
                .queryParams("token", "ATTA4064e5e1b72c92bef8815ae0aa41f3f39310b00e36d19ab3fc413fcf4c0a26a6A77A1CE8")
                .post("https://api.trello.com/1/cards")
                .then()
                .extract().response();

        // Validate the status code
        assertEquals(response.getStatusCode(), 200, "Unexpected status code");

        // Print the response body
        System.out.println("Response Body:\n" + response.getBody().asString());
    }


}
