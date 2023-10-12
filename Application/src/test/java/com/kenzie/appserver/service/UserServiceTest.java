package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class UserServiceTest {

    private UserService userService;
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        userService = mock(UserService.class);
        userRepository = mock(UserRepository.class);
    }

    @Test
    public void findByUserId_successful() {
        // Given
        String id = randomUUID().toString();

        UserRecord userRecord = new UserRecord();
        userRecord.setUserId(id);

        User user = new User();
        user.setUserId(id);

        // When
        when(userService.findByUserId(id)).thenReturn(user);
        User userResult = userService.findByUserId(id);

        // Then
        Assertions.assertEquals(id, userResult.getUserId(), "User ids match");
    }

    @Test
    public void findByUserId_notFound() {
        // Given
        String userId = "123456";

        // When
        when(userService.findByUserId(userId)).thenReturn(null);
        User result = userService.findByUserId(userId);

        // Then
        Assertions.assertNull(result, "user does not exist");
    }

    @Test
    public void createNewUser_successful() {
        // Given
        String username = "bstaats";
        String password = "abc123";
        String email = "brandi.staats@mykenzie.snhu.edu";
        User user = new User(username, password, email);

        // When
        when(userService.createNewUser(user)).thenReturn(user);
        User result = userService.createNewUser(user);

        // Then
        Assertions.assertNotNull(result, "User should not be null");
    }

    @Test
    public void createNewUser_fails() {
        // Given
        User user = new User(null, null, null);

        // When
        when(userService.createNewUser(user)).thenThrow(new IllegalArgumentException());

        //Then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.createNewUser(user),
                "Should throw IllegalArgumentException");
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
        when(userService.updateUser(user)).thenReturn(Optional.of(user));
        Optional<User> result = userService.updateUser(user);

        // Then
        assertTrue("should match user and result", Optional.of(user).equals(result));
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
        when(userService.deleteUser(user.getUserId())).thenReturn(true);
        boolean result = userService.deleteUser(user.getUserId());

        // Then
        assertTrue("User deleted successfully", result);
    }

    @Test
    public void deleteUser_fails() {
        // Given
        UserRecord userRecord = mock(UserRecord.class);

        // When
        when(userRecord.getUserId()).thenReturn(randomUUID().toString());
        when(userService.deleteUser(userRecord.getUserId())).thenThrow(new NullPointerException());

        // Then
        Assertions.assertThrows(NullPointerException.class,
                () -> userService.deleteUser(userRecord.getUserId()),
                "Should throw NullPointerException");
    }
}
