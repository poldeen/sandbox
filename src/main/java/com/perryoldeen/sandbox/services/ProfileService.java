package com.perryoldeen.sandbox.services;

import com.perryoldeen.sandbox.entities.Profile;
import com.perryoldeen.sandbox.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public ResponseEntity<Profile> getProfile(Long id) {
        Optional<Profile> profileData = profileRepository.findById(id);

        if (profileData.isPresent()) {
            return new ResponseEntity<>(profileData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity getProfiles() {
        List<Profile> profiles = profileRepository.findAll();

        return ResponseEntity.ok(profiles);
    }

    public ResponseEntity getProfilesContaining(String firstName) {
        List<Profile> profiles = profileRepository.findByFirstNameContaining(firstName);

        if (profiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(profiles);
    }

    public ResponseEntity<Profile> updateProfile(Long id, Profile profile) {

        Optional<Profile> profileData = profileRepository.findById(id);

        if (profileData.isPresent()) {
            Profile _profile = profileData.get();
            _profile.setFirstName(profile.getFirstName());
            _profile.setLastName(profile.getLastName());
            return new ResponseEntity<>(profileRepository.save(_profile), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteProfile(Long id) {
        try {
            profileRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
