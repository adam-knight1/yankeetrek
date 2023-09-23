package com.kenzie.appserver.service.model;

public class Comment {
    //added by adam
    private final String commentId;
    private final String ownerId;
    private final String title;
    private final String content;
    private final String chatRoomId;

    public Comment(String commentId, String ownerId, String title, String content, String chatRoomId) {
        this.commentId = commentId;
        this.ownerId = ownerId;
        this.title = title;
        this.content = content;
        this.chatRoomId = chatRoomId;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}



