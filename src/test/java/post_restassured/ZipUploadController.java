package post_restassured;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ZipUploadController {

    @Test
    public void createSymmetry() {
        // Set the base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
        RequestSpecification request = RestAssured.given();

        // Add Authorization header for Bearer Token Authentication
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhY3RpdmUiOmZhbHNlLCJyb2xlcyI6WyJCaWRkZXIiXSwiZmlyc3ROYW1lIjoid2FnaCIsInVzZXJJZCI6IjM0NTAwNjAyLTcyNmQtNGViNC05MWI2LTZhZjIxZDllOGRhMiIsImxhc3ROYW1lIjoiRG9lIiwic3ViIjoicHJhc2hhbnQiLCJpYXQiOjE3MzM4OTYyMzksImV4cCI6MTczMzkyNjIzOX0.4rkjo8FBy4DcMFFiuqwNuHo8w5KEjUkNrpqYg7QgSgM";  // Replace with secure handling
        request.header("Authorization", "Bearer " + token);

        // Set the Content-Type header to multipart/form-data
        request.header("Content-Type", "multipart/form-data");

        // Add the file as a multi-part form data
        File file = new File("C:/WorkSpaces/Swastik2425_Workspace/Postsample/Testing/polish_Masters_Excel.xlsx");
        request.multiPart("file", file);

        // Optional: Add other form-data fields if required
        request.multiPart("description", "Sample file upload");
        request.multiPart("userId", "7828500F-5781-40D5-9E61-ADF2A09EB993");

        // Send the POST request
        Response response = request.post("/upload/media");

        // Print the response status and body
        System.out.println("The status received: " + response.statusLine());
        System.out.println("Response: " + response.getBody().asString());

        // Assert status code is 200 or 201
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 200 || statusCode == 201, 
                "Expected 200 OK or 201 Created, but got: " + statusCode);

        // Additional status handling
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
    }
}
