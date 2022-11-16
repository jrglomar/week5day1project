package com.academy.springboot.repository;

import com.academy.springboot.enums.Roles;
import com.academy.springboot.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {
        @Autowired
    UserRepository userRepository;
    User user1, admin, user2;

    @BeforeEach
    void setup() {
        user1 = new User(1L, "ajMapagMahal", "password", Roles.USER);
        admin = new User(2L, "ravenLangMalakas", "password", Roles.ADMIN);
        user2 = new User(3L, "tamaNaMichael", "password", Roles.USER);
    }

    @Test
    public void findAll() {
        //Arrange
        List<User> userList = List.of(user1, admin, user2);
        //Act
        userRepository.saveAll(userList);
        //Assert
        assertThat(userRepository.findAll()).containsAll(userList);
    }

    @Test
    public void findById() {
        //Arrange
        User expectedUser=userRepository.save(user1);
        //Act
        Optional<User> actualUser=userRepository.findById(expectedUser.getId());
        //Assert
        assertTrue(actualUser.isPresent());
        assertEquals(user1,expectedUser);
    }

    @Test
    public void save() {
        //Act
        User expectedUser = userRepository.save(admin);
        //Assert
        assertEquals(admin, expectedUser);
    }
    @Test
    public void delete() {

        User expectedUser = userRepository.save(user1);
        boolean isExistBeforeDelete = userRepository.findById(1L).isPresent();

        //Act
        userRepository.delete(user1);

        //Assert
        assertTrue(isExistBeforeDelete);
        assertTrue(userRepository.findById(1L).isEmpty());

    }
}
