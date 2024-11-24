package com.apiTesting;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class GetTests {
    static {
        RestAssured.baseURI = "https://reqres.in/api";
    }
    @Test
    public void testGetListOfUsers() {
        Response response = 
            given()
                .queryParam("page", 2) // Query Parameter for page number
            .when()
                .get("/users")
            .then()
                .statusCode(200) 
                .extract()
                .response();

        System.out.println("Response for List of Users: " + response.asString());
        Assert.assertTrue(response.jsonPath().getList("data").size() > 0, "User list is empty");
    }
    @Test
    public void testGetSingleUser() {
        Response response = 
            given()
            .when()
                .get("/users/2") // Single User with ID 2
            .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Response for Single User: " + response.asString());
        Assert.assertEquals(response.jsonPath().getInt("data.id"), 2, "User ID does not match");
    }

    // Test case for GET Single User with invalid ID
    @Test
    public void testGetSingleUserInvalid() {
        given()
        .when()
            .get("/users/999") // Non-existing User ID
        .then()
            .statusCode(404); // Expecting 404 Not Found
    }

    // Test case for GET List of Resources
    @Test
    public void testGetListOfResources() {
        Response response = 
            given()
            .when()
                .get("/unknown") // List of Resources
            .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Response for List of Resources: " + response.asString());
        Assert.assertTrue(response.jsonPath().getList("data").size() > 0, "Resource list is empty");
    }

    // Test case for GET Single Resource
    @Test
    public void testGetSingleResource() {
        Response response = 
            given()
            .when()
                .get("/unknown/2") // Single Resource with ID 2
            .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Response for Single Resource: " + response.asString());
        Assert.assertEquals(response.jsonPath().getInt("data.id"), 2, "Resource ID does not match");
    }

    // Test case for GET with Delayed Response
    @Test
    public void testGetDelayedResponse() {
        Response response = 
            given()
                .queryParam("delay", 3) // Delayed response of 3 seconds
            .when()
                .get("/users")
            .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Response for Delayed Users List: " + response.asString());
        Assert.assertTrue(response.jsonPath().getList("data").size() > 0, "User list is empty after delay");
    }
}
