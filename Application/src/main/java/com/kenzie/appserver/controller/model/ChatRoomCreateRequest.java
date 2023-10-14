package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Comment;

import javax.validation.constraints.NotEmpty;

public class ChatRoomCreateRequest {
    @NotEmpty
    @JsonProperty("ownerId")
    private String ownerId;

    @JsonProperty("chatRoomId")
    private String chatRoomId;

    @JsonProperty("timestamp")
    private String timeStamp;

    @JsonProperty("sentComment")
    private String sentComment;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSentComment() {
        return sentComment;
    }

    public void setSentComment(String sentComment) {
        this.sentComment = sentComment;
    }
}
