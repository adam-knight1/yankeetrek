package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Comment;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatRoomResponse {
    @JsonProperty("ownerId")
    private String ownerId;

    @JsonProperty("chatRoomId")
    private String chatRoomId;

    @JsonProperty("timeStamp")
    private String timestamp;

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSentComment() {
        return sentComment;
    }

    public void setSentComment(Comment sentComment) {
        this.sentComment = sentComment;
    }
}
