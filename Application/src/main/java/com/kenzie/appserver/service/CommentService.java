package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.CommentRepository;
import com.kenzie.appserver.repositories.model.CommentRecord;
import com.kenzie.appserver.service.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

    @Service
    public class CommentService {
        private CommentRepository commentRepository;

        //maybe incorporate cache here?

        @Autowired
        public CommentService(CommentRepository commentRepository) { this.commentRepository = commentRepository; }

        public Comment findById(String id) {
            Comment commentFromBackend = commentRepository
                    .findById(id)
                    .map(comment -> new Comment(comment.getCommentId(), comment.getOwnerId(), comment.getTitle(), comment.getContent(),comment.getChatRoomId()) )
                    .orElse(null);
            return commentFromBackend;
        }

        public List<Comment> findAll() {
            List<Comment> comments = new ArrayList<>();
            commentRepository
                    .findAll()
                    .forEach(comment -> comments.add(new Comment(comment.getCommentId(), comment.getOwnerId(), comment.getTitle(), comment.getContent(),comment.getChatRoomId())));
            return comments;
        }

        public Comment addNewComment(Comment comment) {
            CommentRecord commentRecord = new CommentRecord();
            commentRecord.setCommentId(comment.getCommentId());
            commentRecord.setOwnerId(comment.getOwnerId());
            commentRecord.setTitle(comment.getTitle());
            commentRecord.setContent(comment.getContent());

            try {
                commentRepository.save(commentRecord);
                return comment;
            } catch (Exception e){  //maybe add a custom exception class for this -adam
                System.out.println("unable to save comment" + e.getMessage());
                return null;
            }
        }
    }

