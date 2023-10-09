package com.kenzie.appserver.service.model;

import com.kenzie.appserver.repositories.model.CommentRecord;

public class Comment {
    //added by adam
    private String commentId;
    private String ownerId;
    //userId of comment creator
    private String title;
    private String content;
    private String chatRoomId;

    public Comment(String commentId, String ownerId, String title, String content, String chatRoomId) {
        this.commentId = commentId;
        this.ownerId = ownerId;
        this.title = title;
        this.content = content;
        this.chatRoomId = chatRoomId;
    }

    public Comment() {
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

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }
}



