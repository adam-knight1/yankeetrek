package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.CommentResponse;
import com.kenzie.appserver.controller.model.UserCreateRequest;
import com.kenzie.appserver.controller.model.UserResponse;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("userId") String userId) {
        User user = userService.findByUserId(userId);
        if (user == null) {
            return ResponseEntity.ok(userToResponse(user));
        }
        return ResponseEntity.ok(userToResponse(user));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createNewUser(@RequestBody UserCreateRequest userCreateRequest) {
        User user = new User(
          userCreateRequest.getUsername(),
          userCreateRequest.getPassword(),
          userCreateRequest.getEmail()
        );

        try {
            userService.createNewUser(user);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(userToResponse(user));
    }

    private UserResponse userToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId().toString());
        userResponse.setUsername(user.getUsername());
        userResponse.setPassword(user.getPassword());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }

}
