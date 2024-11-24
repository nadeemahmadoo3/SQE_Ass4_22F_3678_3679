package com.apiTesting.test;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import com.apiTesting.api.ResourceApi;
import com.apiTesting.model.Resource;
import com.apiTesting.utils.ApiTestHelper;
import com.apiTesting.data.TestDataProvider;

public class ResourceTests {

    @Test
    public void testGetResources() {
        Response response = ResourceApi.getResources(1); // Get resources on page 1
        ApiTestHelper.assertStatusCode(response, 200);
    }

    @Test
    public void testGetSingleResource() {
        Response response = ResourceApi.getResourceById(2); // Get resource with ID 2
        ApiTestHelper.assertStatusCode(response, 200);
    }

    @Test(dataProvider = "resourceCreationData", dataProviderClass = TestDataProvider.class)
    public void testCreateResource(Resource resource) {
        Response response = ResourceApi.createResource(resource);
        ApiTestHelper.assertStatusCode(response, 201); // 201 Created
    }

    @Test
    public void testUpdateResource() {
        Resource updatedResource = new Resource("Updated Resource", "Manager");
        Response response = ResourceApi.updateResource(2, updatedResource);
        ApiTestHelper.assertStatusCode(response, 200);
        ApiTestHelper.assertResponseContains(response, "name", "Updated Resource");
    }

    @Test
    public void testDeleteResource() {
        Response response = ResourceApi.deleteResource(2);
        ApiTestHelper.assertStatusCode(response, 204); // No Content
    }
}
