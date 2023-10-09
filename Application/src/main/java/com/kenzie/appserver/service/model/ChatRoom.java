package com.kenzie.appserver.service.model;

public class ChatRoom {
    //added by adam
    private final String ownerId;
    private final String chatRoomId;
    private final Long timeStamp;

    public ChatRoom(String ownerId, String chatRoomId, Long timeStamp) {
        this.ownerId = ownerId;
        this.chatRoomId = chatRoomId;
        this.timeStamp = timeStamp;

    }

    public String getUserId() {
        return ownerId;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }
}
