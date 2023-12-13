package com.perryoldeen.sandbox.entities;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    Profile profile;

    @BeforeEach
    public void setUp() {
        profile = new Profile();
    }

    @Test
    public void getProfileId() {
        Long idValue = 4L;

        profile.setProfileId(idValue);

        assertAll(
                () ->  assertEquals(idValue, profile.getProfileId())
        );
    }

    @Test
    public void getExternalId() {
        String value = "123xyz";

        profile.setExternalId(value);

        assertEquals(value, profile.getExternalId());
    }

}