package com.perryoldeen.sandbox.controllers;

import com.perryoldeen.sandbox.dto.JwtResponse;
import com.perryoldeen.sandbox.dto.LoginRequest;
import com.perryoldeen.sandbox.dto.MessageResponse;
import com.perryoldeen.sandbox.dto.SignupRequest;
import com.perryoldeen.sandbox.entities.ERole;
import com.perryoldeen.sandbox.entities.Profile;
import com.perryoldeen.sandbox.entities.ProfileRole;
import com.perryoldeen.sandbox.repositories.ProfileRepository;
import com.perryoldeen.sandbox.repositories.ProfileRoleRepository;
import com.perryoldeen.sandbox.security.jwt.JwtUtils;
import com.perryoldeen.sandbox.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    ProfileRoleRepository profileRoleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
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

        Set<String> strRoles = signUpRequest.getRole();
        Set<ProfileRole> profileRoles = new HashSet<>();

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
