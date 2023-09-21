package com.kenzie.appserver.service.model;

import java.util.UUID;

public class User {
    //just testing git branching - adam
    UUID userID;
    String username;
    String password;
    String email;

    public User() {
        this.userID = UUID.randomUUID();
    }

    public User(String username, String password, String email) {
        this.userID = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
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
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
