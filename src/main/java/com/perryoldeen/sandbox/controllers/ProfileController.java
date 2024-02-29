package com.perryoldeen.sandbox.controllers;

import com.perryoldeen.sandbox.dto.JwtResponse;
import com.perryoldeen.sandbox.dto.LoginRequest;
import com.perryoldeen.sandbox.dto.SignupRequest;
import com.perryoldeen.sandbox.entities.Profile;
import com.perryoldeen.sandbox.services.ProfileAuthenticationService;
import com.perryoldeen.sandbox.services.ProfileRegistrationService;
import com.perryoldeen.sandbox.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    ProfileAuthenticationService profileAuthenticationService;

    @Autowired
    ProfileRegistrationService profileRegistrationService;

    @Autowired
    ProfileService profileService;

    @GetMapping
    public ResponseEntity getProfiles(@RequestParam(required = false) String firstName) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NO_CONTENT);

        if (firstName == null)
            response = profileService.getProfiles();
        else
            response = profileService.getProfilesContaining(firstName);

        return response;

    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable("id") Long id) {

        ResponseEntity<Profile> response = profileService.getProfile(id);

        return response;

    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable("id") Long id, @RequestBody Profile profile) {

        ResponseEntity<Profile> response = profileService.updateProfile(id, profile);

        return response;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProfile(@PathVariable("id") Long id) {
        ResponseEntity<HttpStatus> response = profileService.deleteProfile(id);

        return response;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        JwtResponse response = profileAuthenticationService.authenticateProfile(loginRequest);

        return ResponseEntity.ok(response);

    }

    @PostMapping()
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        ResponseEntity response = profileRegistrationService.registerProfile(signUpRequest);

        return response;
    }

}
