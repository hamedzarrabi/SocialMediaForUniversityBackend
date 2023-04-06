package com.hami.auth;

import com.hami.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthResponse {
    private String email;
    private String accessToken;
    private String firstName;
    private String lastName;
    private Set<Role> roles;
    private Date dateOfBirth;
    private String imageProfile;
    private String userId;
}
