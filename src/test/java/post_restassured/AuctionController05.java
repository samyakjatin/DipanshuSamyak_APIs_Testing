package post_restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuctionController05 {

    @Test
    public void createSymmetry() {
        
        // Set the base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
        RequestSpecification request = RestAssured.given();
        
        // Add Authorization header for Bearer Token Authentication
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhY3RpdmUiOnRydWUsInJvbGVzIjpbIkJpZGRlciJdLCJmaXJzdE5hbWUiOiJ3YWdoIiwidXNlcklkIjoiMzQ1MDA2MDItNzI2ZC00ZWI0LTkxYjYtNmFmMjFkOWU4ZGEyIiwibGFzdE5hbWUiOiJEb2UiLCJzdWIiOiJwcmFzaGFudCIsImlhdCI6MTczNDE2ODM2NywiZXhwIjoxNzM0MTk4MzY3fQ.5MVGjYPJ5mgtd4bIgUA7wWcMn19_cJpCBvgWxwJCYK0";  // Make sure to use the actual token
        request.header("Authorization", "Bearer " + token);  // Add Bearer token in Authorization header
        
       // String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
        String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";
       // String supplierId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";
        //String emailId = "samyakjatin2032@gmail.com";
        // Add path parameters dynamically
       //request.pathParam("userId", userId);
        request.pathParam("auctionId", auctionId);
       // request.pathParam("supplierId", supplierId);
        
        // Set the Content-Type header to application/json
        request.header("Content-Type", "application/json");  
        
        // Create the JSON body for the POST request
       String jsonBody = "{\n" +
                          
                          "}";
        
        // Add the JSON body to the request
       request.body(jsonBody);
        
        // Send the POST request
        Response response = request.post("/auction/sent-emails/{auctionId}");
        
        // Print the response status and body for debugging
        System.out.println("The status received: " + response.statusLine());
        System.out.println("Response: " + response.getBody().asString());  // Added response logging
        System.out.println("---------------Response Details---------------");
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        
        // Check if the status code is 200 (OK) or 201 (Created)
        if (statusCode == 200) {
            System.out.println("Request succeeded: Data inserted successfully.");
        } else if (statusCode == 201) {
            System.out.println("Request succeeded: Symmetry created.");
        } else if (statusCode == 400) {
            System.out.println("Bad Request: Invalid input.");
        } else if (statusCode == 403) {
            System.out.println("Forbidden: Access is denied.");
        } else if (statusCode == 500) {
            System.out.println("Internal Server Error: The server encountered an unexpected condition.");
        }

        // Assert that the status code is 200 (OK) or 201 (Created)
        Assert.assertTrue(statusCode == 200 || statusCode == 201, "Expected 200 OK or 201 Created, but got: " + statusCode);
    }
}