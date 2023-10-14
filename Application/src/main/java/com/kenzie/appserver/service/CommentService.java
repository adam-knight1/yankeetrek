package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.CommentCreateRequest;
import com.kenzie.appserver.repositories.CommentRepository;
import com.kenzie.appserver.repositories.model.CommentRecord;
import com.kenzie.appserver.service.model.Comment;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    //maybe incorporate cache here?  findById or findAll?

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment findById(String id) {
        Comment commentFromBackend = commentRepository
                .findById(id)
                .map(comment -> new Comment(comment.getCommentId(), comment.getOwnerId(), comment.getTitle(), comment.getContent(), comment.getChatRoomId()))
                .orElse(null);

        return commentFromBackend;
    }

    public List<Comment> findAll() {
        List<Comment> comments = new ArrayList<>();
        commentRepository
                .findAll()
                .forEach(comment -> comments.add(new Comment(comment.getCommentId(), comment.getOwnerId(), comment.getTitle(), comment.getContent(), comment.getChatRoomId())));
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
        } catch (IllegalArgumentException e) {  //maybe add a custom exception class for this -adam
            System.out.println("unable to save comment" + e.getMessage());
            return null;
        }
    }

    public Optional<Comment> editComment(String id, CommentCreateRequest request) {
        //using optional to account for null values rather than a bunch of null checks -adam

        Optional<CommentRecord> optionalExistingComment = commentRepository.findById(id);

        if (optionalExistingComment.isPresent()) {
            CommentRecord existingComment = optionalExistingComment.get();

            existingComment.setOwnerId(request.getOwnerId());
            existingComment.setChatRoomId(request.getChatRoomId());
            existingComment.setTitle(request.getTitle());
            existingComment.setContent(request.getContent());

            commentRepository.save(existingComment);

            return Optional.of(transformToComment(existingComment));
        }
        return Optional.empty();
    }

    public boolean deleteComment(String id) {
        try {
            commentRepository.deleteById(id);
            return true;
        } catch (Exception e) { //area for custom exception perhaps?
            System.out.println("Unable to delete comment: " + e.getMessage());
            return false;
        }
    }

    private Comment transformToComment(CommentRecord commentRecord) {
        Comment comment = new Comment();
        comment.setCommentId(commentRecord.getCommentId());
        comment.setOwnerId(commentRecord.getOwnerId());
        comment.setChatRoomId(commentRecord.getChatRoomId());
        comment.setTitle(commentRecord.getTitle());
        comment.setContent(commentRecord.getContent());
        return comment;
    }
}



