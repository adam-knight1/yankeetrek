package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kenzie.appserver.service.model.User;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "FriendsList")
public class FriendsListRecord {

@DynamoDBHashKey(attributeName = "userId")
    private String userId;
@DynamoDBRangeKey(attributeName = "friendsListId")
    private String friendsListId;
@DynamoDBAttribute(attributeName = "friendsIds")
    private List<String> friendsIds;
@DynamoDBAttribute(attributeName = "pendingRequests")
    private List<String> pendingRequests;
@DynamoDBAttribute(attributeName = "blockedUsers")
    private List<String> blockedUsers;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendsListRecord that = (FriendsListRecord) o;
        return Objects.equals(userId, that.userId) && Objects.equals(friendsListId, that.friendsListId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, friendsListId);
    }
}
