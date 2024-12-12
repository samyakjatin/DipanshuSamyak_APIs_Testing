package post_restassured;



import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuctionController03 {

    @Test
    public void createSymmetry() {
        
        // Set the base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
        RequestSpecification request = RestAssured.given();
        
        // Add Authorization header for Bearer Token Authentication
         String token = Config.token;
         request.header("Authorization", "Bearer " + token);  // Add Bearer token in Authorization header
       
        // Set the Content-Type header to application/json
        request.header("Content-Type", "application/json");  
        
        // Create the JSON body for the POST request
        String jsonBody = "{\n" +
        		"  \"name\": \"Year-End Auction\",\n" +
                "  \"inventory\": \"Electronics and Furniture\",\n" +
                "  \"bidExtendInTime\": 120,\n" +
                "  \"bulkbId\": \"bulk_auction_2024\",\n" +
                "  \"proxybId\": \"proxy_bid_456\",\n" +
                "  \"popCornBid\": \"dynamic_bid\",\n" +
                "  \"extendDeadLineType\": \"flexible\",\n" +
                "  \"extendDeadLineValue\": 10,\n" +
                "  \"noOfTimesBidExtend\": 5,\n" +
                "  \"auctionMode\": \"online\",\n" +
                "  \"recordStatus\": \"active\",\n" +
                "  \"venueAddress\": \"Auction Center, 500 Main St, San Francisco, CA\",\n" +
                "  \"viewingDateTime\": \"2024-12-20T09:00:00Z\",\n" +
                "  \"startDateTime\": \"2024-12-21T10:00:00Z\",\n" +
                "  \"endDateTime\": \"2024-12-22T18:00:00Z\",\n" +
                "  \"resultDateTime\": \"2024-12-23T12:00:00Z\",\n" +
                "  \"auctionId\": \"auction_789\",\n" +
                "  \"currency\": \"USD\",\n" +
                "  \"noOfStocks\": 100,\n" +
                "  \"noOfBids\": 250,\n" +
                "  \"status\": \"ACTIVE\",\n" +
                "  \"isAllowedProxy\": true,\n" +
                "  \"isAllowedBulkBid\": true,\n" +
                "  \"exchangeRate\": 1.0,\n" +
                "  \"membershipFee\": 100\n" +
        	    "}";
        
      
        request.body(jsonBody);
        	
        // Send the POST request
        Response response = request.post("/auction");
        
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