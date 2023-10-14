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
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

/*    public User findUserByUserId(String userId) {
        System.out.println("Searching for userId: " + userId);
        User user = userRepository
                .findById(userId)
                .map(u -> new User(u.getUserId())

        if (user == null) {
            System.out.println("User with userId: " + userId + " not found.");
        } else {
            System.out.println("User found: " + user);
        }
        return user;
    }*/

    public User findByUserId(String userId) {
        System.out.println("Searching for userId: " + userId);
        User user = userRepository
                .findById(userId)
                .map(u -> new User(u.getUserId(),u.getUsername(), u.getPassword(), u.getEmail()))
                .orElse(null);
        if (user == null) {
            System.out.println("User with userId: " + userId + " not found.");
        } else {
            System.out.println("User found: " + user);
        }
        return user;
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
