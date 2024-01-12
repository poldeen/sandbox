package com.perryoldeen.sandbox.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perryoldeen.sandbox.dto.JwtResponse;
import com.perryoldeen.sandbox.dto.LoginRequest;
import com.perryoldeen.sandbox.services.ProfileAuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @MockBean
    private ProfileAuthenticationService profileAuthenticationService;

    @Autowired
    private MockMvc mockMvc;

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void authenticateUser() throws Exception {

        LoginRequest request = new LoginRequest();
        request.setUsername("username");
        request.setPassword("password");

        List<String> roleList = new ArrayList<>();
        roleList.add("admin");

        JwtResponse mockResponse = new JwtResponse("13158641165861", 12L, "First", "Last", "Username", "testResponse@testResponse.com", roleList);

        doReturn(mockResponse).when(profileAuthenticationService).authenticateProfile(any());

        mockMvc.perform(post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk());

    }

    @Test
    void registerUser() {
    }
}