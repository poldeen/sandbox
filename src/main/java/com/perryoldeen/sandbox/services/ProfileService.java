package com.perryoldeen.sandbox.services;

import com.perryoldeen.sandbox.entities.Profile;
import com.perryoldeen.sandbox.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public Profile getProfile(String uniqueId) {
        Profile response = new Profile();

        response = profileRepository.getByUniqueId(uniqueId);

        return response;

    }

    public List<Profile> getProfiles() {
        List<Profile> profiles = new ArrayList<>();

        profiles = profileRepository.findAll();

        return profiles;
    }
}
