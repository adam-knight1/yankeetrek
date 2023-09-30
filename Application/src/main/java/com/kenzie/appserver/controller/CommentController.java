package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.CommentCreateRequest;
import com.kenzie.appserver.controller.model.CommentResponse;
import com.kenzie.appserver.service.CommentService;
import com.kenzie.appserver.service.model.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable("id") String id) {
        Comment comment = commentService.findById(id);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(commentToResponse(comment));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CommentResponse>> getAllComments() {
        List<Comment> comments = commentService.findAll();
        List<CommentResponse> responses = comments.stream().map(this::commentToResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<CommentResponse> addNewComment(@RequestBody CommentCreateRequest commentCreateRequest) {
        Comment comment = new Comment(
                randomUUID().toString(),
                commentCreateRequest.getOwnerId(),
                commentCreateRequest.getTitle(),
                commentCreateRequest.getContent(),
                commentCreateRequest.getChatRoomId()
        );

        try {
            commentService.addNewComment(comment);
        } catch (IllegalArgumentException e) {
           // return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(commentToResponse(comment));
    }

    private CommentResponse commentToResponse(Comment comment) {  //convert comment object to comment response object -adam
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setCommentId(comment.getCommentId());
        commentResponse.setOwnerId(comment.getOwnerId());
        commentResponse.setTitle(comment.getTitle());
        commentResponse.setContent(comment.getContent());
        commentResponse.setChatRoomId(comment.getChatRoomId());
        return commentResponse;
    }
}


