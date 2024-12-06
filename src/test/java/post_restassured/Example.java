package post_restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

public class Example {

    @Test
    public void createSymmetry() {
        
        // Set the base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
        RequestSpecification request = RestAssured.given();
        
        // Add Authorization header for Bearer Token Authentication
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIxMDVjODQ0Yi01Y2VmLTQ0YjItYWUzMS05NDFiZmI5NDczNzYiLCJmaXJzdE5hbWUiOiJ3YWdoIiwicm9sZXMiOlsiQmlkZGVyIl0sImFjdGl2ZSI6dHJ1ZSwibGFzdE5hbWUiOiJEb2UiLCJzdWIiOiJwcmFzaGFudCIsImlhdCI6MTczMjg3NTMwOSwiZXhwIjoxNzMyODgxMzA5fQ.MwKpHmg5Y0bcLZ3TY1tySHbdAgOFJ_YHpPLrpiPcdas";  // Make sure to use the actual token
        request.header("Authorization", "Bearer " + token);  // Add Bearer token in Authorization header
        
        // Set the Content-Type header to multipart/form-data
        request.header("Content-Type", "multipart/form-data");  
        
        // Create the multipart body for the POST request
        File file = new File("C:\\Users\\CIRCLE2\\Desktop\\Postsample//sample.txt");
        
        // Add the file to the request
        request.multiPart("file", file);
        
        // Send the POST request
        Response response = request.post("/upload/media");
        
        // Print the response status and body for debugging
        System.out.println("The status received: " + response.statusLine());
        System.out.println("Response: " + response.getBody().asString());  // Added response logging
        System.out.println("---------------Response Details---------------");
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        
        // Check if the status code is 200 (OK) or 201 (Created)
        if (statusCode == 200) {
            System.out.println("Request succeeded: File uploaded successfully.");
        } else if (statusCode == 201) {
            System.out.println("Request succeeded: Symmetry created.");
        } else if (statusCode == 400) {
            System.out.println("Bad Request: Invalid input.");
        }

        // Assert that the status code is 200 (OK) or 201 (Created)
        Assert.assertTrue(statusCode == 200 || statusCode == 201, "Expected 200 OK or 201 Created, but got: " + statusCode);
    }
}