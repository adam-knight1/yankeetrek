package com.kenzie.appserver.repositories.model;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "ChatRoom")
public class ChatRoomRecord {

    @DynamoDBHashKey
    private String userId;

    @DynamoDBAttribute
    private String message;

    @DynamoDBRangeKey
    private Long timeStamp;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getDate() {
        return timeStamp;
    }

    public void setDate(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRoomRecord that = (ChatRoomRecord) o;
        return Objects.equals(userId, that.userId) && Objects.equals(message, that.message)
                && Objects.equals(timeStamp, that.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, message, timeStamp);
    }
}
