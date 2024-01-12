package com.perryoldeen.sandbox.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        String value = "first";

        profile.setFirstName(value);

        assertEquals(value, profile.getFirstName());
    }

    @Test
    void getLastName() {
        String value = "last";

        profile.setLastName(value);

        assertEquals(value, profile.getLastName());
    }

    @Test
    void getUsername() {
        String value = "username";

        profile.setUsername(value);

        assertEquals(value, profile.getUsername());
    }

    @Test
    void getProfileRoles() {
        List<ProfileRole> value = new ArrayList<>();

        ProfileRole profileRole = new ProfileRole();
        profileRole.setRoleName(ERole.ROLE_ADMIN);

        value.add(profileRole);

        profile.setProfileRoles(value);

        assertEquals(profile.getProfileRoles().get(0).getRoleName(), ERole.ROLE_ADMIN);

    }

    @Test
    void profileConstructor() {
        String username = "user";
        String uniqueId = "unique";
        String firstName = "first";
        String lastName = "last";
        String password = "password";

        Profile testedObject = new Profile(username, uniqueId, firstName, lastName, password);

        assertSame(username, testedObject.getUsername());
        assertSame(uniqueId, testedObject.getUniqueId());
        assertSame(firstName, testedObject.getFirstName());
        assertSame(lastName, testedObject.getLastName());
        assertSame(password, testedObject.getPassword());

    }

}