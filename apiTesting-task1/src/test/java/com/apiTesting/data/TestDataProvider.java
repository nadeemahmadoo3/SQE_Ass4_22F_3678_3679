package com.apiTesting.data;

import org.testng.annotations.DataProvider;

import com.apiTesting.model.Resource;
import com.apiTesting.model.User;

public class TestDataProvider {

    @DataProvider(name = "userCreationData")
    public Object[][] userCreationData() {
        return new Object[][] {
            { new User("Nadeem", "Engineer") },
            { new User("Sana", "Manager") },
            { new User("Alina", "Analyst") }
        };
    }

    @DataProvider(name = "invalidUserData")
    public Object[][] invalidUserData() {
        return new Object[][] {
            { new User("", "Engineer") },       // Empty name
            { new User("Sana", "") },          // Empty job
            { new User("Alina", null) }          // Null job
        };
    }
    @DataProvider(name = "resourceCreationData")
    public Object[][] resourceCreationData() {
        return new Object[][] {
            { new Resource("Resource A", "Developer") },
            { new Resource("Resource B", "Manager") },
            { new Resource("Resource C", "Analyst") }
        };
    }
    @DataProvider(name = "invalidResourceData")
    public Object[][] invalidResourceData() {
        return new Object[][] {
            { new Resource("", "Developer") },       // Empty name
            { new Resource("Resource B", "") },      // Empty job
            { new Resource("Resource C", null) }     // Null job
        };
    }
}
