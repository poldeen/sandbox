package com.perryoldeen.sandbox.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageResponseTest {

    MessageResponse messageResponse;

    @BeforeEach
    public void setUp() {
        messageResponse = new MessageResponse("initialized message");
    }

    @Test
    void getMessage() {

        String value = "this is the new message";
        messageResponse.setMessage(value);
        assertEquals(value, messageResponse.getMessage());
    }
}