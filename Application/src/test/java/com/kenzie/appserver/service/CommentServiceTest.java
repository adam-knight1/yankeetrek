package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.CommentCreateRequest;
import com.kenzie.appserver.repositories.CommentRepository;
import com.kenzie.appserver.repositories.model.CommentRecord;
import com.kenzie.appserver.service.model.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommentServiceTest {

    private CommentService commentService;
    private CommentRepository commentRepository;

    @BeforeEach
    void setup() {
        commentService = mock(CommentService.class);
        commentRepository = mock(CommentRepository.class);
    }

    @Test
    public void findById_Successful() {
        // Given
        String id = randomUUID().toString();

        CommentRecord commentRecord = new CommentRecord();
        commentRecord.setCommentId(id);

        Comment comment = new Comment();
        comment.setCommentId(id);

        // When
        when(commentService.findById(id)).thenReturn(comment);
        Comment result = commentService.findById(id);

        // Then
        Assertions.assertEquals(id, result.getCommentId(), "Comment ids should match");
    }

    @Test
    public void findById_NonExistantId_Fails() {
        // Given
        String id = "123456";

        // When
        when(commentService.findById(id)).thenReturn(null);
        Comment result = commentService.findById(id);

        // Then
        Assertions.assertNull(result, "Comment should be null");
    }

    @Test
    public void findAll_Successful() {
        // Given
        String id = randomUUID().toString();
        String id2 = randomUUID().toString();

        Comment comment = new Comment(id, null, null, null, null);
        Comment comment2 = new Comment(id2, null, null, null, null);

        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        commentList.add(comment2);

        // When
        when(commentService.findAll()).thenReturn(commentList);
        List<Comment> results = commentService.findAll();

        // Then
        Assertions.assertEquals(results, commentList, "Comment lists should match");
    }

    @Test
    public void findAll_NoComments_ReturnsNull() {
        // Given/When
        when(commentService.findAll()).thenReturn(null);
        List<Comment> results = commentService.findAll();

        // Then
        Assertions.assertNull(results);
    }

    @Test
    public void addNewComment_Successful() {
        // Given
        Comment comment = new Comment();
        comment.setCommentId(randomUUID().toString());
        comment.setContent("Words");
        comment.setTitle("Bigger Words");

        // When
        when(commentService.addNewComment(comment)).thenReturn(comment);
        Comment result = commentService.addNewComment(comment);

        // Then
        Assertions.assertEquals(comment, result, "Comments should match");
    }

    @Test
    public void addNewComment_Null_ThrowsException()  {
        // Given
        Comment comment = new Comment();

        // When
        when(commentService.addNewComment(comment)).thenThrow(new IllegalArgumentException());

        // Then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> commentService.addNewComment(comment),
                "Should throw IllegalArgumentException");
    }

    @Test
    public void editComment_Successful() {
        // Given
        Comment comment = new Comment(randomUUID().toString(), null, null, null, null);
        commentService.addNewComment(comment);

        CommentCreateRequest commentCreateRequest = new CommentCreateRequest();
        commentCreateRequest.setOwnerId(comment.getOwnerId());
        commentCreateRequest.setContent(comment.getContent());
        commentCreateRequest.setTitle("Updated Title");
        commentCreateRequest.setChatRoomId(comment.getChatRoomId());

        Comment updatedComment = new Comment(comment.getCommentId(),
                comment.getOwnerId(),
                "Updated Title",
                comment.getContent(),
                comment.getChatRoomId());

        Optional<Comment> optionalComment = Optional.of(comment);
        Optional<Comment> updatedOptionalComment = Optional.of(updatedComment);

        // When
        when(commentService.editComment(comment.getCommentId(), commentCreateRequest)).thenReturn(updatedOptionalComment);
        Optional<Comment> result = commentService.editComment(comment.getCommentId(), commentCreateRequest);

        // Then
        Assertions.assertEquals(updatedOptionalComment, result, "The result should be updated");
    }

    @Test
    public void editComment_NonExistent_ReturnsEmpty() {
        // Given
        Comment comment = new Comment();
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest();
        Optional<Comment> expected = Optional.empty();

        // When
        when(commentService.editComment(comment.getCommentId(),
                commentCreateRequest)).thenReturn(Optional.empty());
        Optional<Comment> result = commentService.editComment(comment.getCommentId(),
                commentCreateRequest);

        // Then
        Assertions.assertEquals(expected, result, "Should return Optional.empty");
    }

    @Test
    public void deleteComment_Successful() {
        // Given
        Comment comment = new Comment(randomUUID().toString(), null, null, null, null);

        // When
        when(commentService.deleteComment(comment.getCommentId())).thenReturn(true);
        boolean result = commentService.deleteComment(comment.getCommentId());

        // Then
        Assertions.assertTrue(result, "Result should be true");
    }

    @Test
    public void deleteComment_NonExistent_ReturnsFalse() {
        // Given
        String id = "123456";

        // When
        when(commentService.deleteComment(id)).thenReturn(false);
        boolean result = commentService.deleteComment(id);

        // Then
        Assertions.assertFalse(result, "Result should return false");
    }










}