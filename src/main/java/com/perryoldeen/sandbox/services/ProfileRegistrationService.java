package com.perryoldeen.sandbox.services;

import com.perryoldeen.sandbox.dto.MessageResponse;
import com.perryoldeen.sandbox.dto.SignupRequest;
import com.perryoldeen.sandbox.entities.ERole;
import com.perryoldeen.sandbox.entities.Profile;
import com.perryoldeen.sandbox.entities.ProfileRole;
import com.perryoldeen.sandbox.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileRegistrationService {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    PasswordEncoder encoder;

    public ResponseEntity<?> registerProfile(SignupRequest signUpRequest) {
        if (profileRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (profileRepository.existsByUniqueId(signUpRequest.getUniqueId())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new profile's account
        Profile profile = new Profile(signUpRequest.getUsername(),
                signUpRequest.getUniqueId(),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                encoder.encode(signUpRequest.getPassword()));

        List<String> strRoles = signUpRequest.getRole();
        List<ProfileRole> profileRoles = new ArrayList<>();

        ProfileRole profileRole = new ProfileRole();

        if (strRoles == null) {
            profileRole.setRoleName(ERole.ROLE_USER);
            profileRole.setProfile(profile);
            profileRoles.add(profileRole);
        } else {
            strRoles.forEach(role -> {
                System.out.println(role);

                switch (role) {
                    case "admin":
                        profileRole.setRoleName(ERole.ROLE_ADMIN);
                        profileRole.setProfile(profile);
                        profileRoles.add(profileRole);

                        break;
                    case "mod":
                        profileRole.setRoleName(ERole.ROLE_MODERATOR);
                        profileRole.setProfile(profile);
                        profileRoles.add(profileRole);

                        break;
                    default:
                        profileRole.setRoleName(ERole.ROLE_USER);
                        profileRole.setProfile(profile);
                        profileRoles.add(profileRole);
                }
            });
        }

        profile.setProfileRoles(profileRoles);

        profileRepository.save(profile);

        return ResponseEntity.ok(new MessageResponse("Profile registered successfully!"));
    }
}
