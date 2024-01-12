package com.perryoldeen.sandbox.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SignupRequestTest {

    SignupRequest signupRequest;

    @BeforeEach
    public void setUp() {
        signupRequest = new SignupRequest();
    }

    @Test
    void getFirstName() {
        String value = "First";
        signupRequest.setFirstName(value);
        assertEquals(value, signupRequest.getFirstName());
    }

    @Test
    void getLastName() {
        String value = "Last";
        signupRequest.setLastName(value);
        assertEquals(value, signupRequest.getLastName());
    }

    @Test
    void getUsername() {
        String value = "Username";
        signupRequest.setUsername(value);
        assertEquals(value, signupRequest.getUsername());
    }

    @Test
    void getUniqueId() {
        String value = "UniqueId";
        signupRequest.setUniqueId(value);
        assertEquals(value, signupRequest.getUniqueId());
    }

    @Test
    void getRole() {
        String value = "admin";
        List<String> roleList = new ArrayList<>();
        roleList.add(value);

        signupRequest.setRole(roleList);
        assertEquals(value, signupRequest.getRole().get(0));
    }

    @Test
    void getPassword() {
        String value = "password$123";
        signupRequest.setPassword(value);
        assertEquals(value, signupRequest.getPassword());
    }
}