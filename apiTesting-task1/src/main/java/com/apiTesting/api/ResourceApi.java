package com.apiTesting.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.apiTesting.model.Resource;

public class ResourceApi {

    private static final String BASE_URL = "https://reqres.in/api";

    // GET all resources
    public static Response getResources(int page) {
        return RestAssured.given()
                          .param("page", page)
                          .when()
                          .get(BASE_URL + "/resources");
    }

    // GET single resource by ID
    public static Response getResourceById(int id) {
        return RestAssured.given()
                          .when()
                          .get(BASE_URL + "/resources/" + id);
    }

    // POST (create) resource
    public static Response createResource(Resource resource) {
        return RestAssured.given()
                          .header("Content-Type", "application/json")
                          .body(resource)
                          .when()
                          .post(BASE_URL + "/resources");
    }

    // PUT (update) resource by ID
    public static Response updateResource(int id, Resource resource) {
        return RestAssured.given()
                          .header("Content-Type", "application/json")
                          .body(resource)
                          .when()
                          .put(BASE_URL + "/resources/" + id);
    }

    // DELETE resource by ID
    public static Response deleteResource(int id) {
        return RestAssured.given()
                          .when()
                          .delete(BASE_URL + "/resources/" + id);
    }
}
