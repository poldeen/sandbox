package com.perryoldeen.sandbox.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

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
                () -> assertEquals(idValue, profile.getProfileId())
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

        Set<ProfileRole> profileRoles = new HashSet<>();
        ProfileRole profileRole = new ProfileRole();

        profileRole.setRoleName(ERole.ROLE_ADMIN);

        profileRoles.add(profileRole);

        profile.setProfileRoles(profileRoles);

        System.out.print(profileRoles.size());

        assertEquals(1, profileRoles.size());

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

    @Test
    void getFirstName() {
    }

    @Test
    void getLastName() {
    }

    @Test
    void getUsername() {
    }
    
}