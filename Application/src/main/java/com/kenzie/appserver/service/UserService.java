package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public User findByUserId(String userId) {
        User userFromBackend = userRepository
                .findById(userId)
                .map(user -> new User(user.getUsername(), user.getPassword(), user.getEmail()))
                .orElse(null);
        return userFromBackend;
    }


}
