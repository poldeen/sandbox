package com.perryoldeen.sandbox.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OptimisticLock;

import java.util.ArrayList;
import java.util.List;

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
    @NotNull
    @Column(name = "profileId")
    private Long profileId;

    @Column(name = "externalId")
    private String externalId;

    @Column(name = "uniqueId", nullable = false, unique = true)
    private String uniqueId;

    @Column(name = "password", length = 120)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    @OrderBy(value = "profileRoleId")
    @OptimisticLock(excluded = true)
    private List<ProfileRole> profileRoles = new ArrayList<>();

}
