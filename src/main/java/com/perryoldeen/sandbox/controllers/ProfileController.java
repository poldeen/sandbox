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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
public class ProfileController {



    @Autowired
    ProfileAuthenticationService profileAuthenticationService;

    @Autowired
    ProfileRegistrationService profileRegistrationService;

    @Autowired
    ProfileService profileService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        JwtResponse response = profileAuthenticationService.authenticateProfile(loginRequest);

        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        ResponseEntity response = profileRegistrationService.registerProfile(signUpRequest);

        return response;
    }

    @GetMapping
    public ResponseEntity getProfile(String uniqueId){

        Profile response = profileService.getProfile(uniqueId);

        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity getProfiles(){
        List<Profile> response =  profileService.getProfiles();
        return ResponseEntity.ok(response);

    }
}
