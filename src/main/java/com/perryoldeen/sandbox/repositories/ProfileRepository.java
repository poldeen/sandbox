package com.perryoldeen.sandbox.repositories;

import com.perryoldeen.sandbox.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByUniqueId(String uniqueId);

    List<Profile> findByFirstNameContaining(String firstName);

    //Profile findByProfileId(Long profileId);

    // Profile getByUniqueId(String uniqueId);
}
