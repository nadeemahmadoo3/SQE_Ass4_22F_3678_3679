package com.apiTesting.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.apiTesting.model.User;

import static io.restassured.RestAssured.given;

public class UserApi {
    
    private static final String BASE_URL = "https://reqres.in/api";
    
    // GET request to get a list of users
    public static Response getUsers(int page) {
        return given()
                .queryParam("page", page)
                .when()
                .get(BASE_URL + "/users");
    }
    
    
    // GET request to get a single user by ID
    public static Response getUserById(int userId) {
        return given()
                .when()
                .get(BASE_URL + "/users/" + userId);
    }
    
    // POST request to create a new user
    public static Response createUser(User user) {
        return given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(BASE_URL + "/users");
    }
    
    // PUT request to update user by ID
    public static Response updateUser(int userId, User user) {
        return given()
                .contentType("application/json")
                .body(user)
                .when()
                .put(BASE_URL + "/users/" + userId);
    }
    
    // DELETE request to delete a user by ID
    public static Response deleteUser(int userId) {
        return given()
                .when()
                .delete(BASE_URL + "/users/" + userId);
    }
 // Method to handle successful user login
    public static Response loginUser(String email, String password) {
        return RestAssured.given()
                          .header("Content-Type", "application/json")
                          .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                          .when()
                          .post(BASE_URL + "/login");
    }
    // Method to handle unsuccessful user login
    public static Response loginUserInvalid(String email) {
        return RestAssured.given()
                          .header("Content-Type", "application/json")
                          .body("{\"email\": \"" + email + "\"}")
                          .when()
                          .post(BASE_URL + "/login");
    }
    // Method to get users with delayed response
    public static Response getUsersWithDelay(int delayInSeconds) {
        return given()
                .queryParam("delay", delayInSeconds)
                .when()
                .get("/users")
                .then()
                .extract()
                .response();
    }
}
