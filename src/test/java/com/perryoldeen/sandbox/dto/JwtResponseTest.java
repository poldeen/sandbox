package com.perryoldeen.sandbox.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtResponseTest {

    JwtResponse jwtResponse;

    String accessToken = "!23123213123325";
    Long id = 10L;
    String firstName = "first";
    String lastName = "last";
    String username = "user";
    String email = "test@test.com";
    List<String> roles = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        roles.add("admin");
        jwtResponse = new JwtResponse(accessToken, id, firstName, lastName, username, email, roles);
    }

    @Test
    void jwtResponseConstructor() {
        assertEquals(accessToken, jwtResponse.getToken());
        assertEquals(id, jwtResponse.getId());
        assertEquals(firstName, jwtResponse.getFirstName());
        assertEquals(lastName, jwtResponse.getLastName());
        assertEquals(username, jwtResponse.getUsername());
        assertEquals(email, jwtResponse.getEmail());
        assertEquals("admin", jwtResponse.getRoles().get(0));
    }

    @Test
    void getToken() {
        String value = "168465189461dfafferaffa";
        jwtResponse.setToken(value);
        assertEquals(value, jwtResponse.getToken());
    }

    @Test
    void getType() {
        String value = "jwt";
        jwtResponse.setType(value);
        assertEquals(value, jwtResponse.getType());
    }

    @Test
    void getId() {
        Long value = 13L;
        jwtResponse.setId(value);
        assertEquals(value, jwtResponse.getId());

    }

    @Test
    void getFirstName() {
        String value = "firstUpdated";
        jwtResponse.setFirstName(value);
        assertEquals(value, jwtResponse.getFirstName());
    }

    @Test
    void getLastName() {
        String value = "lastUpdated";
        jwtResponse.setLastName(value);
        assertEquals(value, jwtResponse.getLastName());
    }

    @Test
    void getUsername() {
        String value = "userUpdated";
        jwtResponse.setUsername(value);
        assertEquals(value, jwtResponse.getUsername());
    }

    @Test
    void getEmail() {
        String value = "emailUpdated";
        jwtResponse.setEmail(value);
        assertEquals(value, jwtResponse.getEmail());
    }

    @Test
    void getRoles() {
        String value = "user";
        roles.add(value);
        jwtResponse.setRoles(roles);
        assertEquals(value, jwtResponse.getRoles().get(1));

    }
}