package com.perryoldeen.sandbox.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void getProfileRoles() {

        List<ProfileRole> profileRoles = new ArrayList<>();
        ProfileRole profileRole = new ProfileRole();
        String profileRoleValue = "Test Role";
        profileRole.setRole(profileRoleValue);

        profileRoles.add(profileRole);

        profile.setProfileRoles(profileRoles);

        assertEquals(profileRoleValue, profile.getProfileRoles().get(0).getRole());

    }

    @Test
    public void getUniqueId() {
        String value = "123xyz";

        profile.setUniqueId(value);

        assertEquals(value, profile.getUniqueId());
    }

    @Test
    public void getPassword() {
        String value = "123xyz";

        profile.setPassword(value);

        assertEquals(value, profile.getPassword());
    }

}