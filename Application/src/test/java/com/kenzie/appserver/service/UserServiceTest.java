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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

@Test
    public void findByUserId_successful() {
        // Given
        String userId = "adam";
        UserRecord userRecord = new UserRecord();
        userRecord.setUserId(userId);
        userRecord.setUsername("adamUser");
        userRecord.setEmail("adam@dogs.com");
        userRecord.setPassword("superSecretPassword");

        when(userRepository.findById(userId)).thenReturn(Optional.of(userRecord));

        User returnedUser = userService.findByUserId(userId);

        assertNotNull(returnedUser, "Returned user should not be null");
        assertEquals(userId, returnedUser.getUserId(), "User IDs should match");
        assertEquals("adamUser", returnedUser.getUsername(), "Usernames should match");
    }

    @Test
    public void addNewUser_successful() {
        User newUser = new User("UserGuy", "newUser", "newPassword", "new@cooltown.com");

        User returnedUser = userService.createNewUser(newUser);

        assertNotNull(returnedUser, "Returned user should not be null");
        assertEquals(newUser.getUserId(), returnedUser.getUserId(), "User IDs should match");
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
        assertNotNull(result, "User should not be null");
    }

    @Test
    public void updateUser_successful() {
        String userId = "awesomePossum";
        User updatedUser = new User(userId, "Userz", "passmast", "updated@sillyville.com");
        UserRecord existingUserRecord = new UserRecord();
        existingUserRecord.setUserId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUserRecord));

        Optional<User> returnedUser = userService.updateUser(userId, updatedUser);

        if (!returnedUser.isPresent()) {
            fail("Returned user should be present");
        }
        assertEquals(updatedUser.getUsername(), returnedUser.get().getUsername(), "Usernames should match");
    }

    @Test
    public void deleteUser_successful() {
        String userId = "snoozerId";
        UserRecord existingUserRecord = new UserRecord();
        existingUserRecord.setUserId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUserRecord));

        boolean isDeleted = userService.deleteUser(userId);

        if (!isDeleted){
            fail("User not deleted successfully");
        }
    }


    @Test
    public void findByUserId_notFound() {
        // Given
        String userId = "coolGuyPaul";

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When
        User returnedUser = userService.findByUserId(userId);

        // Then
        assertNull(returnedUser, "Returned user should be null for non-existing ID");
    }


    @Test
    public void CreateNewUser_SaveThrowsException() {
        // Mock userRepository.save() to throw an exception
        doThrow(new RuntimeException("forced error")).when(userRepository).save(any(UserRecord.class));

        User user = new User("AdamHeartsBanjo", "adamsEmail", "banjoDog", "funWithCats");
        assertNull(userService.createNewUser(user), "Should return null when save throws exception");
    }



}



   /* @Test
    public void createNewUser_fails() {
        // Given
        User user = new User(null, null, null);

        // When
        when(userService.createNewUser(user)).thenThrow(new IllegalArgumentException());

        //Then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.createNewUser(user),
                "Should throw IllegalArgumentException");
    }*/

    /*@Test
    public void updateUser_successful() {
        String id = "1232";
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
        when(userService.updateUser(id, user)).thenReturn(Optional.of(user));
        Optional<User> result = userService.updateUser(id, user);

        // Then
        assertTrue("should match user and result", Optional.of(user).equals(result));
    }
*/
   /* @Test
    public void updateUser_fails() {
        // Given
        String id = "!2355";
        String username = "maya";
        String password = "dog";
        String email = "maya@gmail.com";
        User user = new User(username, password, email);

        // When
        Optional<User> result = userService.updateUser(id,user);

        // Then
        assertThrows(NullPointerException.class, Executable.class.cast(result));
    }*/

    /*@Test
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
    }*/

     /*@Test
    public void findByUserId_notFound() {
        String userId = "123456";

        when(userService.findByUserId(userId)).thenReturn(null);
        User result = userService.findByUserId(userId);

        Assertions.assertNull(result, "user does not exist");
    }*/

    /* @Test
    public void deleteUser_fails() {
        String userId = "snoozerId";
        UserRecord existingUserRecord = new UserRecord();
        existingUserRecord.setUserId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUserRecord));

        boolean isnotDeleted = userService.deleteUser(userId);

        if (isnotDeleted){
            fail("failed to delete user");
        }
    }*/






