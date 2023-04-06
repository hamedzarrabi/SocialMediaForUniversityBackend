package com.hami;

import com.hami.entity.Role;
import com.hami.entity.User;
import com.hami.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

//@Test
//    public void testCreateUser() {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String rawPassword = "hamed2023";
//        String encodedPassword = passwordEncoder.encode(rawPassword);
//
//        User newUser = new User("hamed","zarrabi",
//                "hamed.zarrabi87@gmail.com", encodedPassword, "hamedPicture",
//                "09124431480", new Date(), "esfahan");
//
//        User savedUser = userRepository.save(newUser);
//        assertThat(savedUser).isNotNull();
//        assertThat(savedUser.getId()).isGreaterThan(0);
//    }

//    @Test
//    public void testAssignRolesToUser() {
//        Long userId = 1l;
//        long roleId = 2l;
//
//        User user = userRepository.findById(userId).get();
//        user.addRole(new Role(roleId));
//
//        User updatedUser = userRepository.save(user);
//
////        assertThat(updatedUser.getRoles()).hasSize(1);
//    }
}
