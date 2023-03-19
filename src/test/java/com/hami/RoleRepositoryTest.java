package com.hami;

import com.hami.entity.Role;
import com.hami.entity.User;
import com.hami.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {
    @Autowired private RoleRepository roleRepository;

    @Test
    public void testCreatedRoles() {

        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");

        roleRepository.saveAll(List.of(admin, user));

        long numberOfRoles = roleRepository.count();

        assertEquals(2, numberOfRoles);
    }

    @Test
    public void testListRoles() {
        List<Role> roleList = roleRepository.findAll();
        assertThat(roleList.size()).isGreaterThan(0);

        roleList.forEach(System.out::println);
    }

}
