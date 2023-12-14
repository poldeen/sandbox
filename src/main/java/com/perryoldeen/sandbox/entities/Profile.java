package com.perryoldeen.sandbox.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OptimisticLock;

import java.util.HashSet;
import java.util.Set;

/**
 * This entity class represents a profile of a user.
 *
 * @author poldeen
 * @since 12/12/23
 */
@Getter
@Setter
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profileId", nullable = false)
    private Long profileId;

    @Column(name = "externalId")
    private String externalId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "uniqueId", nullable = false, unique = true)
    private String uniqueId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 120)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile", fetch = FetchType.LAZY)
    @OrderBy(value = "profileRoleId")
    @OptimisticLock(excluded = true)
    private Set<ProfileRole> profileRoles = new HashSet<>();

    public Profile() {
    }

    public Profile(String username, String uniqueId, String firstName, String lastName, String password) {
        this.username = username;
        this.uniqueId = uniqueId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}
