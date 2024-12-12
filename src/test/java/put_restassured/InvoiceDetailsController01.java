package put_restassured;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class InvoiceDetailsController01 {

    @Test
    public void updateUser() {
        
        // Set the base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
        RequestSpecification request = RestAssured.given();
        
        // Add query parameters for GET request (username, password, lotDiamondInfo)
        request.queryParam("username", "dhaval.sharma");
        request.queryParam("password", "samyak@2024");
        
        // Add Authorization header for Bearer Token Authentication
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhY3RpdmUiOmZhbHNlLCJsYXN0TmFtZSI6IkRvZSIsInVzZXJJZCI6IjM0NTAwNjAyLTcyNmQtNGViNC05MWI2LTZhZjIxZDllOGRhMiIsImZpcnN0TmFtZSI6IndhZ2giLCJyb2xlcyI6WyJCaWRkZXIiXSwic3ViIjoicHJhc2hhbnQiLCJpYXQiOjE3MzM5ODE1OTYsImV4cCI6MTczNDAxMTU5Nn0.v3wBh_C9xINYDpE5um5d05rZbwDeEUxbjhwZLWdcV8s";  // Make sure to use the actual token
        request.header("Authorization", "Bearer " + token);  // Add Bearer token in Authorization header
        
        // Set the Content-Type header to application/json
        request.header("Content-Type", "application/json");  
        
        // Create the JSON body for the PUT request
        String jsonBody = "{\n" +
                "  \"ledgerType\": \"string\",\n" +
                "  \"ledgerAmount\": 0,\n" +
                "  \"companyName\": \"string\",\n" +
                "  \"ledgerId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
                "  \"name\": \"string\",\n" +
                "  \"desc\": \"string\",\n" +
                "  \"code\": \"string\",\n" +
                "  \"srNo\": 0,\n" +
                "  \"countryName\": \"string\",\n" +
                "  \"percentage\": 0,\n" +
                "  \"active\": true,\n" +
                "  \"countryId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" +
                "}";

        // Add the JSON body to the request
        request.body(jsonBody);
        
      String auctionId = "3fa85f64-5717-4562-b3fc-2c963f66afa6"; 
   String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
      
      
           request.pathParam("userId", userId)
           .pathParam("auctionId", auctionId);
        
        // Send the PUT request
        Response response = request.put("/make-hold/{userId}/{auctionId}");
        
        // Print the response status and body for debugging
        System.out.println("The status received: " + response.statusLine());
        System.out.println("Response: " + response.getBody().asString());
        
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        
        // Check if the status code is 200 (OK)
        if (statusCode == 200) {
            System.out.println("Request succeeded: User updated successfully.");
        } else if (statusCode == 400) {
            System.out.println("Bad Request: Invalid input.");
        } else if (statusCode == 403) {
            System.out.println("Forbidden: Access is denied.");
        } else if (statusCode == 404) {
            System.out.println("Not Found: The resource could not be found.");
        } else if (statusCode == 500) {
            System.out.println("Internal Server Error: The server encountered an unexpected condition.");
        }

        // Assert that the status code is 200 (OK)
        Assert.assertTrue(statusCode == 200, "Expected 200 OK, but got: " + statusCode);
    }
}
