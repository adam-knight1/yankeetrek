package com.kenzie.appserver.service.model;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    //created by adam
    UUID userId;
    String username;
    String password;
    String email;

    public User() {
        this.userId = UUID.randomUUID();
    }

    public User(String username, String password, String email) {
        this.userId = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
