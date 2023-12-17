package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import java.util.Random;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ApiStepDefinitions {

    private Response response;
    private String idList;
    private String lastPart;
    private String cardID1;
    private String cardID2;

    @Then("create a board")
    public void createBoard() {

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

        // Extract the "shortUrl" parameter using JSONPath
        String shortUrl = response.jsonPath().getString("shortUrl");

        // Extract the last part of the "shortUrl" using regular expression
        String lastPart = extractLastPart(shortUrl);
        String lastpart2 = extractLastPart(shortUrl)+"/lists";
        // Print the extracted last part of the shortUrl
        System.out.println("Last Part of Short Url: " + lastPart);

       // Response response2 = given()
       //         .header("Accept", "application/json")
       //         .header("Content-Type", "application/json")
       //         .queryParams("key", "40de027b492cbb68c2f0394afcf1e6e0")
         //       .queryParams("token", "ATTA4064e5e1b72c92bef8815ae0aa41f3f39310b00e36d19ab3fc413fcf4c0a26a6A77A1CE8")
           //     .post("https://api.trello.com/1/boards/" + lastpart2 )
             //   .then()
               // .extract().response();

//        assertEquals(response2.getStatusCode(), 200, "Unexpected status code");

        // Print the response body
  //      System.out.println("Response Body:\n" + response2.getBody().asString());

    //    String idList = response.jsonPath().getString("id");
      //  System.out.println("Id list:\n" + idList);
    }
    private static String extractLastPart(String fullUrl) {
        // Use regular expression to extract the last part after the last '/'
        return fullUrl.replaceAll(".*/", "");
    }
    @Then("create a card")
    public void createCard() {
        // Make a GET request to the /tokens/{token} endpoint
        Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .queryParams("name","beymen1")
                .queryParams("idList", "657ee3ffe2d078c112916512")
                .queryParams("key", "40de027b492cbb68c2f0394afcf1e6e0")
                .queryParams("token", "ATTA4064e5e1b72c92bef8815ae0aa41f3f39310b00e36d19ab3fc413fcf4c0a26a6A77A1CE8")
                .post("https://api.trello.com/1/cards")
                .then()
                .extract().response();

        // Validate the status code
        assertEquals(response.getStatusCode(), 200, "Unexpected status code");

        // Print the response body
        System.out.println("Response Body:\n" + response.getBody().asString());
        String cardID1 = response.jsonPath().getString("shortLink");

        Response response2 = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .queryParams("name","beymen2")
                .queryParams("idList", "657ee3ffe2d078c112916512")
                .queryParams("key", "40de027b492cbb68c2f0394afcf1e6e0")
                .queryParams("token", "ATTA4064e5e1b72c92bef8815ae0aa41f3f39310b00e36d19ab3fc413fcf4c0a26a6A77A1CE8")
                .post("https://api.trello.com/1/cards")
                .then()
                .extract().response();

        // Validate the status code
        assertEquals(response2.getStatusCode(), 200, "Unexpected status code");

        // Print the response body
        System.out.println("Response Body:\n" + response2.getBody().asString());
        String cardID2 = response2.jsonPath().getString("shortLink");
        System.out.println(("here shortlink 2 and 1 \n" + cardID2 + cardID1));
    }

    @Then("update a card")
    public void updateCard(){
        int randomlyChosenNumber = new Random().nextInt(2) + 1;

        // Perform actions based on the chosen number
        if (randomlyChosenNumber == 1) {
            // Code for when 1 is chosen
            Response response = given()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .queryParams("name","beymendegistirdik")
                    .queryParams("key", "40de027b492cbb68c2f0394afcf1e6e0")
                    .queryParams("token", "ATTA4064e5e1b72c92bef8815ae0aa41f3f39310b00e36d19ab3fc413fcf4c0a26a6A77A1CE8")
                    .put("https://api.trello.com/1/cards/" + cardID1)
                    .then()
                    .extract().response();

            // Validate the status code
            assertEquals(response.getStatusCode(), 200, "Unexpected status code");

            // Print the response body
            System.out.println("Response Body:\n" + response.getBody().asString());
            // Add your code statements for Action A here
        } else {
            Response response = given()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .queryParams("name","beymendegistirdik")
                    .queryParams("key", "40de027b492cbb68c2f0394afcf1e6e0")
                    .queryParams("token", "ATTA4064e5e1b72c92bef8815ae0aa41f3f39310b00e36d19ab3fc413fcf4c0a26a6A77A1CE8")
                    .put("https://api.trello.com/1/cards/" + cardID2)
                    .then()
                    .extract().response();

            // Validate the status code
            assertEquals(response.getStatusCode(), 200, "Unexpected status code");

            // Print the response body
            System.out.println("Response Body:\n" + response.getBody().asString());
        }


    }

     @And("delete card")
     public void deleteCard(){
         Response response = given()
                 .header("Accept", "application/json")
                 .header("Content-Type", "application/json")
                 .queryParams("key", "40de027b492cbb68c2f0394afcf1e6e0")
                 .queryParams("token", "ATTA4064e5e1b72c92bef8815ae0aa41f3f39310b00e36d19ab3fc413fcf4c0a26a6A77A1CE8")
                 .delete("https://api.trello.com/1/cards/" + cardID1)
                 .then()
                 .extract().response();

         // Validate the status code
         assertEquals(response.getStatusCode(), 200, "Unexpected status code");

         // Print the response body
         System.out.println("Response Body:\n" + response.getBody().asString());

         Response response2 = given()
                 .header("Accept", "application/json")
                 .header("Content-Type", "application/json")
                 .queryParams("key", "40de027b492cbb68c2f0394afcf1e6e0")
                 .queryParams("token", "ATTA4064e5e1b72c92bef8815ae0aa41f3f39310b00e36d19ab3fc413fcf4c0a26a6A77A1CE8")
                 .delete("https://api.trello.com/1/cards/FO6p8m5W" + cardID2)
                 .then()
                 .extract().response();

         // Validate the status code
         assertEquals(response2.getStatusCode(), 200, "Unexpected status code");

         // Print the response body
         System.out.println("Response Body:\n" + response.getBody().asString());


     }

    @And("delete board")
    public void deleteBoard(){
        Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .queryParams("key", "40de027b492cbb68c2f0394afcf1e6e0")
                .queryParams("token", "ATTA4064e5e1b72c92bef8815ae0aa41f3f39310b00e36d19ab3fc413fcf4c0a26a6A77A1CE8")
                .put("https://api.trello.com/1/boards/" + lastPart )
                .then()
                .extract().response();

        // Validate the status code
        assertEquals(response.getStatusCode(), 200, "Unexpected status code");

        // Print the response body
        System.out.println("Response Body:\n" + response.getBody().asString());

    }





}
