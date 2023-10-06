package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;

import com.kenzie.appserver.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

    public Optional<User> updateUser(User user) {
        Optional<UserRecord> optionalExistingUser = userRepository.findById(user.getUserId().toString());

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

    public boolean deleteUser(UserRecord userRecord) {
        try {
            userRepository.delete(userRecord);
            return true;
        } catch (Exception e) {
            System.out.println("Unable to delete user: " + e.getMessage());
            return false;
        }
    }

    private User transformToUser(UserRecord userRecord) {
        User user = new User();
        user.setUserId(userRecord.getUserId());
        user.setEmail(userRecord.getEmail());
        user.setUsername(userRecord.getUsername());
        user.setPassword(userRecord.getPassword());
        return user;
    }

}
