package com.kenzie.appserver.service.model;

public class Comment {
    //added by adam

        private final String commentId;
        private final String owner;
        private final String title;
        private final String content;

        public Comment(String commentId, String owner, String title, String content) {
            this.commentId = commentId;
            this.owner = owner;
            this.title = title;
            this.content = content;
        }

        public String getId() { return this.commentId; }

        public String getOwner() { return this.owner; }

        public String getTitle() { return this.title; }

        public String getContent() { return this.content; }
    }

