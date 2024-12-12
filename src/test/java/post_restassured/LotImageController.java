package post_restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LotImageController {

    @Test
    public void createSymmetry() {
        
        // Set the base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
        RequestSpecification request = RestAssured.given();
        
        // Add Authorization header for Bearer Token Authentication
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhY3RpdmUiOmZhbHNlLCJsYXN0TmFtZSI6IkRvZSIsInVzZXJJZCI6IjM0NTAwNjAyLTcyNmQtNGViNC05MWI2LTZhZjIxZDllOGRhMiIsImZpcnN0TmFtZSI6IndhZ2giLCJyb2xlcyI6WyJCaWRkZXIiXSwic3ViIjoicHJhc2hhbnQiLCJpYXQiOjE3MzM5ODk3NTcsImV4cCI6MTczNDAxOTc1N30.Ro8j1skSWLUk4IkSOFJRTImnUpR7FUZ7OsM_0ISWOfU";  // Replace with actual token
        request.header("Authorization", "Bearer " + token);  // Add Bearer token in Authorization header
        
        // Set the Content-Type header to application/json
        request.header("Content-Type", "application/json");  
        
        // Create the JSON body for the POST request
        String jsonBody = "{\n" +
            "  \"image\": \"dipanshu\",\n" +
            "  \"video\": \"dipanshu\",\n" +
            "  \"remark\": \"123\"\n" + // Removed trailing comma
            "}";
        
        // Add the JSON body to the request
        request.body(jsonBody);
        
        // Send the POST request
        Response response = request.post("/lot-image");
        
        // Print the response status and body for debugging
        System.out.println("The status received: " + response.statusLine());
        System.out.println("Response: " + response.getBody().asString());
        System.out.println("---------------Response Details---------------");
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        
        // Handle different status codes
        switch (statusCode) {
            case 200:
                System.out.println("Request succeeded: Data inserted successfully.");
                break;
            case 201:
                System.out.println("Request succeeded: Symmetry created.");
                break;
            case 400:
                System.out.println("Bad Request: Invalid input.");
                break;
            case 403:
                System.out.println("Forbidden: Access is denied.");
                break;
            case 500:
                System.out.println("Internal Server Error: The server encountered an unexpected condition.");
                break;
            default:
                System.out.println("Unexpected status code received: " + statusCode);
        }

        // Assert that the status code is 200 (OK) or 201 (Created)
        Assert.assertTrue(statusCode == 200 || statusCode == 201, "Expected 200 OK or 201 Created, but got: " + statusCode);
    }
}
