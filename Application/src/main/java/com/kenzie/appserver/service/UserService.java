package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.UserRepository;
<<<<<<< HEAD
=======
import com.kenzie.appserver.repositories.model.UserRecord;
>>>>>>> main
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

<<<<<<< HEAD
=======
    public User createNewUser(User user) {
        UserRecord userRecord = new UserRecord();
        userRecord.setUserId(user.getUserId());
        userRecord.setEmail(user.getEmail());
        userRecord.setPassword(user.getPassword());
        userRecord.setUsername(user.getUsername());

        try {
            userRepository.save(userRecord);
            return user;
        } catch (Exception e) { //custom exception
            System.out.println("unable to save user" + e.getMessage());
            return null;
        }
    }
>>>>>>> main

}
