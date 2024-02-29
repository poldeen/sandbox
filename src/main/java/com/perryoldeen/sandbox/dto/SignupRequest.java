package com.perryoldeen.sandbox.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class SignupRequest {

    @Size(max = 20)
    private String firstName;

    @Size(max = 20)
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 100)
    private String username;

    @Size(max = 100)
    private String uniqueId;

    private List<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}
