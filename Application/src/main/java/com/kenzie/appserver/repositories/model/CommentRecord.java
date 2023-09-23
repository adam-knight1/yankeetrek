package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;


@DynamoDBTable(tableName = "Comment")
    public class CommentRecord {

        @DynamoDBHashKey
        private String commentId;

        @DynamoDBAttribute
        private String chatRoomId;

        @DynamoDBAttribute
        private String content;

        @DynamoDBAttribute
        private String ownerId;

        @DynamoDBAttribute
        private String title;

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }

        public String getChatRoomId() {
            return chatRoomId;
        }

        public void setChatRoomId(String chatRoomId) {
            this.chatRoomId = chatRoomId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(String ownerId) {
            this.ownerId = ownerId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    @Override
    public boolean equals(Object o) {  // we can change what this compares if need be -adam
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentRecord that = (CommentRecord) o;
        return Objects.equals(commentId, that.commentId) && Objects.equals(ownerId, that.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, ownerId);
    }
}










