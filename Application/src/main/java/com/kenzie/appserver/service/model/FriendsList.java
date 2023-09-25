package com.kenzie.appserver.service.model;

import java.util.List;

public class FriendsList {

    private String userId;
    private String friendsListId;
    private List<String> friendsIds;
    private List<String> pendingRequests;
    private List<String> blockedUsers;


    public FriendsList(String userId, String friendsListId, List<String> friendsIds, List<String> pendingRequests, List<String> blockedUsers) {
        this.userId = userId;
        this.friendsListId = friendsListId;
        this.friendsIds = friendsIds;
        this.pendingRequests = pendingRequests;
        this.blockedUsers = blockedUsers;
    }

    public void addFriendToList(String friendId) {
        if (!this.friendsIds.contains(friendId) &&
                !this.pendingRequests.contains(friendId) &&
                !this.blockedUsers.contains(friendId)) {
            this.friendsIds.add(friendId);
        } else {
            System.out.println("The friend could not be added");
            //throw a custom exception here maybe?
        }
    }

    public void deleteFriendFromList(String friendId){
        if (friendsIds.contains(friendId)) {
            friendsIds.remove(friendId);
        } else {
            System.out.println("Friend could not be deleted, throw a custom exception here"); //need to add a custom exception
        }
    }


    //going to modify these getter and setters for encapsulation/abstraction
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendsListId() {
        return friendsListId;
    }

    public void setFriendsListId(String friendsListId) {
        this.friendsListId = friendsListId;
    }

    public List<String> getFriendsIds() {
        return friendsIds;
    }

    public void setFriendsIds(List<String> friendsIds) {
        this.friendsIds = friendsIds;
    }

    public List<String> getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(List<String> pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    public List<String> getBlockedUsers() {
        return blockedUsers;
    }

    public void setBlockedUsers(List<String> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }
}
