package com.kenzie.appserver.service.model;

public class ChatRoom {
    //added by adam
    private final String ownerId;
    private final String chatRoomId;
    private final String timeStamp;
    private final String sentComment;

    public ChatRoom(String ownerId, String chatRoomId, String timeStamp, String sentComment) {
        this.ownerId = ownerId;
        this.chatRoomId = chatRoomId;
        this.timeStamp = timeStamp;
        this.sentComment = sentComment;

    }


    public String getOwnerId() {
        return ownerId;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getSentComment() {
        return sentComment;
    }


    public void addComment(Comment newComment) {
    }
}
