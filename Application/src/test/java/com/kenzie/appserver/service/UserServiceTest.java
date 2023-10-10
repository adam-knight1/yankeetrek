package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.UserController;
import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonParser;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonNode;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.annotation.JsonAppend;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.*;

public class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRecord userRecord;

    @Autowired
    private final ObjectMapper mapper = new ObjectMapper();



    @Test
    public void findByUserId_successful() throws IOException {
        // Given
        String username = "bstaats";
        String password = "abc123";
        String email = "brandi.staats@mykenzie.snhu.edu";
        User user = new User(username, password, email);

        userRecord = new UserRecord();
        userRecord.setUserId(user.getUserId());
        userRecord.setUsername(user.getUsername());
        userRecord.setEmail(user.getEmail());
        userRecord.setPassword(user.getPassword());

        userRepository.save(userRecord);
        userService.createNewUser(user);

        // When
        User userResult = userService.findByUserId(user.getUserId().toString());

        // Then
        assert userResult == user;
    }

    @Test
    public void findByUserId_notFound() {
        // Given
        String userId = "123456";

        // When
        User result = userService.findByUserId(userId);

        // Then
        assert result == null;
    }

    @Test
    public void createNewUser_successful() {
        // Given
        String username = "bstaats";
        String password = "abc123";
        String email = "brandi.staats@mykenzie.snhu.edu";
        User user = new User(username, password, email);

        // When
        User result = userService.createNewUser(user);

        // Then
        assert result != null;
    }

    @Test
    public void createNewUser_fails() {
        // Given
        User user = new User(null, null, null);

        // When
        try {
            User result = userService.createNewUser(user);
        } catch (IllegalArgumentException e) {
            System.out.println("creating new user failed" + e.getMessage());
        }
    }

    @Test
    public void updateUser_successful() {
        // Given
        String username = "bstaats";
        String password = "abc123";
        String email = "brandi.staats@mykenzie.snhu.edu";
        User user = new User(username, password, email);

        userRecord.setUserId(user.getUserId());
        userRecord.setUsername(username);
        userRecord.setPassword(password);
        userRecord.setEmail(email);

        userRepository.save(userRecord);

        // When
        Optional<User> result = userService.updateUser(user);

        // Then
        assertTrue("should match user and result", user.toString().equals(result.toString()));
    }

    @Test
    public void updateUser_fails() {
        // Given
        String username = "bstaats";
        String password = "abc123";
        String email = "brandi.staats@mykenzie.snhu.edu";
        User user = new User(username, password, email);

        // When
        Optional<User> result = userService.updateUser(user);

        // Then
        assertThrows(NullPointerException.class, Executable.class.cast(result));
    }


}
