package com.apiTesting.utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class ApiTestHelper {

    // Reusable method to assert the status code
    public static void assertStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, 
            "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode());
    }
    
    // Reusable method to assert the response body
    public static void assertResponseContains(Response response, String expectedKey, String expectedValue) {
        Assert.assertEquals(response.jsonPath().getString(expectedKey), expectedValue,
            "Expected " + expectedKey + " to be " + expectedValue + " but was " + response.jsonPath().getString(expectedKey));
    }
    // Method to assert that the list under a specific JSON path is not empty
    public static void assertListNotEmpty(Response response, String jsonPathKey) {
        int size = response.jsonPath().getList(jsonPathKey).size();
        Assert.assertTrue(size > 0, "List under key '" + jsonPathKey + "' is empty!");
    }
}
