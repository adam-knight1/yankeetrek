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
    private String personId;

    @DynamoDBAttribute
    private String message;

    @DynamoDBAttribute
    private String topicName;

    @DynamoDBAttribute
    private List<Comment> commentList;

    @DynamoDBAttribute
    private String chatRoomId;

    @DynamoDBRangeKey
    private Long timeStamp;

    public String getTopicName() {
        return topicName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
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
        return Objects.equals(personId, that.personId) && Objects.equals(message, that.message) && Objects.equals(topicName, that.topicName) && Objects.equals(commentList, that.commentList) && Objects.equals(chatRoomId, that.chatRoomId) && Objects.equals(timeStamp, that.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, message, topicName, commentList, chatRoomId, timeStamp);
    }
}
