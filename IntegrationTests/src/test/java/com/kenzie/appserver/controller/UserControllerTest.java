package com.kenzie.appserver.controller;

import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.UserResponse;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static java.util.UUID.randomUUID;

@IntegrationTest
public class UserControllerTest {
    @Autowired
    UserController userController;

    @Autowired
    UserService userService;

    @Test
    public void getUser_successful() {
        // Given
        User user = new User();
        userService.createNewUser(user);


        // When
        ResponseEntity<UserResponse> result = userController.getUser(user.getUserId().toString());

        // Then

    }
}
