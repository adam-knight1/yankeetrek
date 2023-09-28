package com.kenzie.appserver.repositories.model;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kenzie.appserver.service.model.Comment;
import software.amazon.eventstream.HeaderValue;

import java.util.*;

@DynamoDBTable(tableName = "ChatRoom")
public class ChatRoomRecord implements Map<String, HeaderValue> {

    @DynamoDBHashKey
    public String userId;

    @DynamoDBAttribute
    public String message;

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
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public HeaderValue get(Object key) {
        return null;
    }

    @Override
    public HeaderValue put(String key, HeaderValue value) {
        return null;
    }

    @Override
    public HeaderValue remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends HeaderValue> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<HeaderValue> values() {
        return null;
    }

    @Override
    public Set<Entry<String, HeaderValue>> entrySet() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRoomRecord that = (ChatRoomRecord) o;
        return Objects.equals(userId, that.userId) && Objects.equals(message, that.message) && Objects.equals(topicName, that.topicName) && Objects.equals(commentList, that.commentList) && Objects.equals(chatRoomId, that.chatRoomId) && Objects.equals(timeStamp, that.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, message, topicName, commentList, chatRoomId, timeStamp);
    }
}
