package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;

import com.kenzie.appserver.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public User findByUserId(String userId) {
        Optional<UserRecord> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserRecord user = optionalUser.get();
            System.out.println("User ID: " + user.getUserId());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Password: " + user.getPassword());
            System.out.println("Email: " + user.getEmail());
        } else {
            System.out.println("User not found");
        }

        return userRepository
                .findById(userId)
                .map(user -> new User(user.getUsername(), user.getPassword(), user.getEmail()))
                .orElse(null);



    }

    public User createNewUser(User user) {
        UserRecord userRecord = new UserRecord();
        userRecord.setUserId(user.getUserId());
        userRecord.setEmail(user.getEmail());
        userRecord.setPassword(user.getPassword());
        userRecord.setUsername(user.getUsername());

        if (userRecord.getUserId() != null ||
                userRecord.getEmail() != null ||
                userRecord.getPassword() != null ||
                userRecord.getUsername() != null) {
            try {
                userRepository.save(userRecord);
                return user;
                } catch (Exception e) {
                System.out.println("unable to save user" + e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    public Optional<User> updateUser(User user) {
        Optional<UserRecord> optionalExistingUser = userRepository.findById(user.getUserId());

        if (optionalExistingUser.isPresent()) {
            UserRecord existingUser = optionalExistingUser.get();

            existingUser.setUserId(user.getUserId());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setUsername(user.getUsername());

            userRepository.save(existingUser);

            return Optional.of(transformToUser(existingUser));
        }
        return Optional.empty();
    }

    public boolean deleteUser(String userId) {
        try {
            Optional<UserRecord> optionalUserRecord = userRepository.findById(userId);
            if (optionalUserRecord.isPresent()) {
                userRepository.delete(optionalUserRecord.get());
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            System.out.println("Unable to delete user: " + e.getMessage());
            return false;
        }
    }


    public User transformToUser(UserRecord userRecord) {
        User user = new User();
        user.setUserId(userRecord.getUserId());
        user.setEmail(userRecord.getEmail());
        user.setUsername(userRecord.getUsername());
        user.setPassword(userRecord.getPassword());
        return user;
    }

}
