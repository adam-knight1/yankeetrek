package com.kenzie.appserver.service.model;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    //added by adam
    private final String userId;
    private final String message;
    private final String topicName;
    private List<Comment> commentList = new ArrayList<>();
    //it did not like this list being final...
    // List<String> userList = new ArrayList<>();
    //create hobby class?

    private final String chatRoomId;
    private final Long timeStamp;

    public ChatRoom(String userId, String message, String topicName, String chatRoomId, Long timeStamp, List<Comment> commentList) {
        this.userId = userId;
        this.message = message;
        this.topicName = topicName;
        this.chatRoomId = chatRoomId;
        this.timeStamp = timeStamp;
        this.commentList = commentList;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public String getTopicName() {
        return topicName;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }
}
