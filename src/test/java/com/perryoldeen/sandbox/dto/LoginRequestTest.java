package com.perryoldeen.sandbox.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginRequestTest {

    LoginRequest loginRequest;

    @BeforeEach
    public void setUp() {
        loginRequest = new LoginRequest();
    }

    @Test
    void getUsername() {
        String value = "user";
        loginRequest.setUsername(value);
        assertEquals(value, loginRequest.getUsername());

    }

    @Test
    void getPassword() {
        String value = "password";
        loginRequest.setPassword(value);
        assertEquals(value, loginRequest.getPassword());

    }

}