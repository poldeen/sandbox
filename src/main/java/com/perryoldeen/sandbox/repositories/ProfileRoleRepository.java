package com.perryoldeen.sandbox.repositories;

import com.perryoldeen.sandbox.entities.ProfileRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRoleRepository extends JpaRepository<ProfileRole, Long> {

}
