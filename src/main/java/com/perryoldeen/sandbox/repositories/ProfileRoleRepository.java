package com.perryoldeen.sandbox.repositories;

import com.perryoldeen.sandbox.entities.ERole;
import com.perryoldeen.sandbox.entities.ProfileRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRoleRepository extends JpaRepository<ProfileRole, Long> {
    Optional<ProfileRole> findByRoleName(ERole role);

}
