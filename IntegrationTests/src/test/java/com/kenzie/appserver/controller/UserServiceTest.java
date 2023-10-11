package com.kenzie.appserver.controller;

import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByUserId_successful() {
        // Given
        UUID id = randomUUID();

        UserRecord userRecord = new UserRecord();
        userRecord.setUserId(id);

        userRepository.save(userRecord);

        // When
        User userResult = userService.findByUserId(id.toString());


        // Then
        assert userResult.getUserId() == id;
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
            userService.createNewUser(user);
        } catch (IllegalArgumentException e) {
            System.out.println("creating new user failed" + e.getMessage());
        }
    }

    @Test
    public void updateUser_successful() {
        // Given
        String username = "brandis";
        String password = "cat";
        String email = "brandi@gmail.com";
        User user = new User(username, password, email);

        UserRecord userRecord = new UserRecord();
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
        String username = "maya";
        String password = "dog";
        String email = "maya@gmail.com";
        User user = new User(username, password, email);

        // When
        Optional<User> result = userService.updateUser(user);

        // Then
        assertThrows(NullPointerException.class, Executable.class.cast(result));
    }

    @Test
    public void deleteUser_successful() {
        // Given
        String username = "jacob";
        String password = "lizard";
        String email = "jacob@gmail.com";
        User user = new User(username, password, email);

        userService.createNewUser(user);

        UserRecord userRecord = new UserRecord();
        userRecord.setUserId(user.getUserId());
        userRecord.setUsername(user.getUsername());
        userRecord.setPassword(user.getPassword());
        userRecord.setEmail(user.getEmail());

        // When
        boolean result = userService.deleteUser(userRecord);

        // Then
        assertTrue("User deleted successfully", result);
    }

    @Test
    public void deleteUser_fails() {
        // Given
        UserRecord userRecord = new UserRecord();

        // When
        boolean result = userService.deleteUser(userRecord);

        // Then
        assertThrows(NullPointerException.class, Executable.class.cast(result));
    }
}
