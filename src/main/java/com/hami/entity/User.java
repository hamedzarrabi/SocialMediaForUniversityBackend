package com.hami.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String userId;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true, length = 50)
    @Email
    private String email;
    @Column(nullable = false, length = 64)
    private String password;
    @Column(name = "image_profile")
    private String imageProfile;
    @CreationTimestamp
    @Column(name = "date_register")
    private Date dateRegister;
    private Date dateOfBirth;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String userId, String firstName, String lastName, String email, String password, String imageProfile, Date dateRegister, Date dateOfBirth) {

        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.imageProfile = imageProfile;
        this.dateRegister = dateRegister;
        this.dateOfBirth = dateOfBirth;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
