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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createUser(@RequestParam("firstName") String firstName,
                                        @RequestParam("lastName") String lastName,
                                        @RequestParam("email") String email,
                                        @RequestParam("password") String password,
                                        @RequestParam("dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
                                        @RequestParam("imageProfile") MultipartFile imageProfile) {

        User user = new User();

        if (userRepository.existsByEmail(email)) {
            return new ResponseEntity<>("Email is already taken.", HttpStatus.BAD_REQUEST);
        } else {
            SecureRandom random = new SecureRandom();

            String imageAddress = "images/Profiles/" + email; // + "/" + imageProfile.getOriginalFilename();
            String originalAddressImage = "I:/Project/Instagram/frontend/public/images/Profiles/" + email;

            user.setPassword(passwordEncoder.encode(password));
            user.setUserId(String.valueOf(random.nextInt(90000)) + 1);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setDateOfBirth(dateOfBirth);

            user.setImageProfile(imageAddress + "/" + imageProfile.getOriginalFilename());

            saveImageProfile(imageProfile, originalAddressImage);

            Role role = roleRepository.findByName("ROLE_USER").get();
            user.addRole(role);

            User newUser = userService.createUser(user);

            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
    }

    private String saveImageProfile(MultipartFile image, String imageAddress) {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        try {
            Path path = Paths.get(imageAddress);
            Files.createDirectories(path);
            Files.copy(image.getInputStream(), path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            return "Success image save";
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", e);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            User user = (User) authenticate.getPrincipal();

            String accessToken = jwtTokenUtil.generateAccessToken(user);

            AuthResponse response = new AuthResponse(user.getEmail(), accessToken, user.getFirstName(), user.getLastName(), user.getRoles(), user.getDateOfBirth(), user.getImageProfile(), user.getUserId());

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
