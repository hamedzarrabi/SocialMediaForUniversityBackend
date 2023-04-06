package com.hami.controller;

import com.hami.auth.AuthRequest;
import com.hami.auth.AuthResponse;
import com.hami.auth.JwtTokenUtil;
import com.hami.entity.Role;
import com.hami.entity.User;
import com.hami.repository.RoleRepository;
import com.hami.repository.UserRepository;
import com.hami.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private UserService userService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("Email is already taken.", HttpStatus.BAD_REQUEST);
        }
        SecureRandom random = new SecureRandom();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserId(String.valueOf(random.nextInt(90000)) + 1);

        Role role = roleRepository.findByName("ROLE_USER").get();
        user.addRole(role);

        User newUser = userService.createUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            User user = (User) authenticate.getPrincipal();

            String accessToken = jwtTokenUtil.generateAccessToken(user);

            AuthResponse response = new AuthResponse(user.getEmail(), accessToken, user.getFirstName(), user.getLastName(), user.getRoles(), user.getDateOfBirth(),  user.getImageProfile(), user.getUserId());

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAllUser() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable("userId") String userId) {
        User user = userService.displayUserMetaDate(userId);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}
