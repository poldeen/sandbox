package com.perryoldeen.sandbox.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * This entity class represents the one or many roles a profile can have.
 *
 * @author poldeeen
 * @since 12/12/23
 */

@Getter
@Setter
@Entity
@Table(name = "profileRole")
public class ProfileRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profileRoleId", nullable = false)
    private Long profileRoleId;

    @ManyToOne
    @JoinColumn(name = "profileId")
    @JsonIgnore
    private Profile profile;

    @Column(name = "roleCd")
    private String role;

}
