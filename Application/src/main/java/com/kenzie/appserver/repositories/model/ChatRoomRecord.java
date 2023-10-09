package com.kenzie.appserver.repositories.model;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kenzie.appserver.service.model.Comment;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "ChatRoom")
public class ChatRoomRecord {

    @DynamoDBHashKey
    private String ownerId;

    @DynamoDBRangeKey
    private String topicName;

    @DynamoDBAttribute
    private String comment;

    @DynamoDBAttribute
    private String chatRoomId;

    @DynamoDBAttribute
    private Long timeStamp;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRoomRecord that = (ChatRoomRecord) o;
        return Objects.equals(ownerId, that.ownerId) && Objects.equals(topicName, that.topicName) && Objects.equals(comment, that.comment) && Objects.equals(chatRoomId, that.chatRoomId) && Objects.equals(timeStamp, that.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId, topicName, comment, chatRoomId, timeStamp);
    }
}
