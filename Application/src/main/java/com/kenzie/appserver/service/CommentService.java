package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.CommentRepository;
import com.kenzie.appserver.service.model.Comment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class CommentService {
    @Service
    public class CommentService {
        private CommentRepository commentRepository;

        public CommentService(CommentRepository commentRepository) { this.commentRepository = commentRepository; }

        public Comment findById(String id) {
            Comment commentFromBackend = commentRepository
                    .findById(id)
                    .map(comment -> new Comment(comment.getId(), comment.getOwner(), comment.getTitle(), comment.getContent()) )
                    .orElse(null);
            return commentFromBackend;
        }

        public List<Comment> findAll() {
            List<Comment> comments = new ArrayList<>();
            commentRepository
                    .findAll()
                    .forEach(comment -> comments.add(new Comment(comment.getId(), comment.getOwner(), comment.getTitle(), comment.getContent())));
            return comments;
        }

        public Comment addNewComment(Comment comment) {
            CommentRecord commentRecord = new CommentRecord();
            commentRecord.setId(comment.getId());
            commentRecord.setOwner(comment.getOwner());
            commentRecord.setTitle(comment.getTitle());
            commentRecord.setContent(comment.getContent());
            commentRepository.save(commentRecord);
            return comment;
        }
    }
}
