package com.perryoldeen.sandbox.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileRoleTest {
    ProfileRole profileRole;

    @BeforeEach
    public void setUp() {
        profileRole = new ProfileRole();
    }

    @Test
    void getProfileRoleId() {
        Long idValue = 4L;

        profileRole.setProfileRoleId(idValue);

        assertAll(
                () -> assertEquals(idValue, profileRole.getProfileRoleId())
        );
    }

    @Test
    void getProfile() {
        Profile profileArray = new Profile();
        Long profileIdValue = 4L;
        profileArray.setProfileId(profileIdValue);

        profileRole.setProfile(profileArray);

        assertEquals(profileIdValue, profileRole.getProfile().getProfileId());
    }

    @Test
    void getRole() {
        String value = "123xyz";

        profileRole.setRole(value);

        assertEquals(value, profileRole.getRole());
    }
}