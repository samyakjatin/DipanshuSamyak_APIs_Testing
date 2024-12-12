package post_restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RemarkController {

    @Test
    public void createSymmetry() {
        
        // Set the base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
        RequestSpecification request = RestAssured.given();
        
        // Add Authorization header for Bearer Token Authentication
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhY3RpdmUiOmZhbHNlLCJsYXN0TmFtZSI6IkRvZSIsInVzZXJJZCI6IjM0NTAwNjAyLTcyNmQtNGViNC05MWI2LTZhZjIxZDllOGRhMiIsImZpcnN0TmFtZSI6IndhZ2giLCJyb2xlcyI6WyJCaWRkZXIiXSwic3ViIjoicHJhc2hhbnQiLCJpYXQiOjE3MzM5OTM4OTAsImV4cCI6MTczNDAyMzg5MH0.hHgatbrU9QyXUxk7MCtzwRDn8ZNc-ccr9Xhc-xW8yIA";  // Replace with actual token
        request.header("Authorization", "Bearer " + token);  // Add Bearer token in Authorization header
        
        // Set the Content-Type header to application/json
        request.header("Content-Type", "application/json");  
        
        String jsonBody = "{\n" +
        	    "  \"bidId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
        	    "  \"lotId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
        	    "  \"auctionId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
        	    "  \"userId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
        	    "  \"remark\": \"dipanshu\"\n" + // No trailing comma here
        	    "}";

       

        // Add the JSON body to the request
        request.body(jsonBody);
        
        // Send the POST request
        Response response = request.post("/remark");
        
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
