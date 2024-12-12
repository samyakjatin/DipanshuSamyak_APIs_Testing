package put_restassured;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuctionDetailController {

    @Test
    public void updateUser() {

        // Set the base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1";
        RequestSpecification request = RestAssured.given();

        // Add query parameters
        request.queryParam("username", "dhaval.sharma");
        request.queryParam("password", "samyak@2024");

        // Add Authorization header
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhY3RpdmUiOmZhbHNlLCJsYXN0TmFtZSI6IkRvZSIsInVzZXJJZCI6IjM0NTAwNjAyLTcyNmQtNGViNC05MWI2LTZhZjIxZDllOGRhMiIsImZpcnN0TmFtZSI6IndhZ2giLCJyb2xlcyI6WyJCaWRkZXIiXSwic3ViIjoicHJhc2hhbnQiLCJpYXQiOjE3MzM5ODE1OTYsImV4cCI6MTczNDAxMTU5Nn0.v3wBh_C9xINYDpE5um5d05rZbwDeEUxbjhwZLWdcV8s"; // Replace with actual valid token
        request.header("Authorization", "Bearer " + token);

        // Set Content-Type header
        request.header("Content-Type", "application/json");

        // JSON body
        String jsonBody = "{\n" +
                "  \"auctionName\": \"dipanshu\",\n" +
                "  \"fromRange\": \"05\",\n" +
                "  \"toRange\": \"10\",\n" +
                "  \"incrementAmount\": \"5\"\n" +
                "}";

        // Add the JSON body to the request
        request.body(jsonBody);

        // Set the path parameter
        String auctionId = "3fa85f64-5717-4562-b3fc-2c963f66afa6";
        request.pathParam("auctionId", auctionId);

        // Send the PUT request
        Response response = request.put("/auction-details/{auctionId}");

        // Print response details
        System.out.println("The status received: " + response.statusLine());
        System.out.println("Response Body: ");
        response.prettyPrint();

        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);

        // Handle specific status codes
        switch (statusCode) {
            case 200:
                System.out.println("Request succeeded: User updated successfully.");
                break;
            case 400:
                System.out.println("Bad Request: Invalid input.");
                break;
            case 403:
                System.out.println("Forbidden: Access is denied.");
                break;
            case 404:
                System.out.println("Not Found: The resource could not be found.");
                break;
            case 500:
                System.out.println("Internal Server Error: The server encountered an unexpected condition.");
                break;
            default:
                System.out.println("Unexpected status code: " + statusCode);
        }

        // Assert that the status code is 200 (OK)
        Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
    }
}
