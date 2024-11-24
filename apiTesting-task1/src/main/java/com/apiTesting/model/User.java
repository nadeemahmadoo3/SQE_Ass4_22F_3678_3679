package com.apiTesting.model;

public class User {
    private String name;
    private String job;

    // Constructor that takes two arguments (name and job)
    public User(String name, String job) {
        this.name = name;
        this.job = job;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
