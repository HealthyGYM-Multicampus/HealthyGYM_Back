package com.mul.HealthyGYM.Dto;

public class UserRequest {
    private String authToken;

    public UserRequest() {
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
