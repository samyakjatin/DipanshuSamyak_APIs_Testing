package post_restassured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class symmetry_controller_02 {

    
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com"; // Replace with your API's base URL
    }

    @Test
    public void testPostApi() {
        // Create JSON request payload
        JSONObject requestParams = new JSONObject();
        requestParams.put("desc", "string");
        requestParams.put("code", "string");
        requestParams.put("symmetryId","3fa85f64-5717-4562-b3fc-2c963f66afa6");
        requestParams.put("name", "string");
        requestParams.put("count", "0");
        requestParams.put("createdDate", "string");
        
        // Send POST request
        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestParams.toString())
                .when()
                .post("/symmetry")
                .then()
                .statusCode(201) // Assert that status code is 201
                .body("desc", equalTo("string"))
                .body("code", equalTo("string"))
                .body("symmetryId", equalTo("3fa85f64-5717-4562-b3fc-2c963f66afa6"))
                .body("name", equalTo("string"))
                .body("count", equalTo("0"))
                .body("body", equalTo("bar"))
                .body("createdDate", equalTo("string"))
                .extract().response();

        // Print response
        System.out.println("Response Body is =>  " + response.asString());
    }
}