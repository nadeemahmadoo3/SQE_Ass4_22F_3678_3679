package com.apiTesting.test;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import com.apiTesting.api.UserApi;
import com.apiTesting.model.User;
import com.apiTesting.utils.ApiTestHelper;
import com.apiTesting.data.TestDataProvider;


public class UserTests {

    @Test
    public void testGetUsers() {
        Response response = UserApi.getUsers(2); // Get users on page 2
        ApiTestHelper.assertStatusCode(response, 200);
    }

    @Test(dataProvider = "userCreationData", dataProviderClass = TestDataProvider.class)
    public void testCreateUser(User user) {
        Response response = UserApi.createUser(user);
        ApiTestHelper.assertStatusCode(response, 201); // 201 Created
    }

    @Test
    public void testUpdateUser() {
        User updatedUser = new User("Nadeem", "Developer");
        Response response = UserApi.updateUser(2, updatedUser);
        ApiTestHelper.assertStatusCode(response, 200);
        ApiTestHelper.assertResponseContains(response, "name", "Nadeem");
    }

    @Test
    public void testDeleteUser() {
        Response response = UserApi.deleteUser(2);
        ApiTestHelper.assertStatusCode(response, 204); // No Content
    }
    @Test
    public void testLoginUserValid() {
        // Valid email and password for login
        String email = "eve.holt@reqres.in";
        String password = "cityslicka";

        Response response = UserApi.loginUser(email, password);
        ApiTestHelper.assertStatusCode(response, 200); // Expect HTTP 200 OK
        ApiTestHelper.assertResponseContains(response, "token", response.jsonPath().getString("token"));

        System.out.println("Response for Successful Login: " + response.asString());
    }
    // Test case for unsuccessful login with invalid credentials
    @Test
    public void testLoginUserInvalid() {
        // Invalid email for login (missing password)
        String email = "eve.holt@reqres.in";

        Response response = UserApi.loginUserInvalid(email);
        ApiTestHelper.assertStatusCode(response, 400); // Expect HTTP 400 Bad Request
        ApiTestHelper.assertResponseContains(response, "error", "Missing password");

        System.out.println("Response for Unsuccessful Login: " + response.asString());
    }
    /*
    // Test case for GET with Delayed Response using modularized UserApi
    @Test
    public void testGetDelayedResponse() {
        // Call the UserApi to get users with a delayed response of 3 seconds
        Response response = UserApi.getUsersWithDelay(3);

        // Use the helper method to assert the status code is 200
        ApiTestHelper.assertStatusCode(response, 200);

        // Additional assertion to ensure that the user list is not empty
        ApiTestHelper.assertListNotEmpty(response, "data");

        // Print the response for debugging (optional)
        System.out.println("Response for Delayed Users List: " + response.asString());
    }*/
}
