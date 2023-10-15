package com.kenzie.appserver.controller;

import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.CommentCreateRequest;
import com.kenzie.appserver.service.CommentService;
import com.kenzie.appserver.service.model.Comment;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class CommentControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    CommentService commentService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getComment_Successful() throws Exception {
        Comment comment = new Comment(randomUUID().toString(),
                randomUUID().toString(),
                "title", "content",
                randomUUID().toString());
        Comment persistedComment = commentService.addNewComment(comment);
        mvc.perform(get("/{commentId}", persistedComment.getCommentId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("commentId")
                        .value(is(comment.getCommentId())))
                .andExpect(status().isOk());
    }

    @Test
    public void getComment_NullId_ReturnsNotFound() throws Exception {
        Comment comment = new Comment();

        mvc.perform(get("/{commentId}", comment.getCommentId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllComments_Successful() throws Exception {
        Comment comment = new Comment(randomUUID().toString(),
                randomUUID().toString(),
                "title", "content",
                randomUUID().toString());
        Comment comment2 = new Comment(randomUUID().toString(),
                randomUUID().toString(),
                "title2", "content2",
                randomUUID().toString());

        Comment persistedComment = commentService.addNewComment(comment);
        Comment persistedComment2 = commentService.addNewComment(comment2);

        mvc.perform(get("/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("commentId")
                        .value(is(comment.getCommentId())))
                .andExpect(MockMvcResultMatchers.jsonPath("commentId")
                        .value(is(comment2.getCommentId())))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllComments_NoComments_ReturnsNullResponse() throws Exception {
        Comment comment = new Comment();
        mvc.perform(get("/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("commentId")
                        .value(is(comment.getCommentId())))
                .andExpect(status().isOk());
    }

    @Test
    public void addNewComment_Successful() throws Exception {
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest();
        commentCreateRequest.setChatRoomId(randomUUID().toString());
        commentCreateRequest.setTitle("title");
        commentCreateRequest.setContent("content");
        commentCreateRequest.setOwnerId(randomUUID().toString());

        mvc.perform(post("/comment")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(commentCreateRequest)))
                .andExpect(MockMvcResultMatchers.jsonPath("title")
                        .exists())
                .andExpect(MockMvcResultMatchers.jsonPath("ownerId")
                        .value(is(commentCreateRequest.getOwnerId())))
                .andExpect(status().isOk());
    }

    @Test
    public void addNewComment_Null_ThrowsIllegalArgumentException() throws Exception {
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest();

        mvc.perform(post("/comment")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(commentCreateRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteComment_ExistingComment_Successful() throws Exception {
        Comment comment = new Comment();
        comment.setCommentId(randomUUID().toString());
        commentService.addNewComment(comment);

        mvc.perform(delete("/comment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteComment_NonExistentComment_Fails() throws Exception {
        mvc.perform(delete("/comment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("commentId")
                        .doesNotExist())
                .andExpect(status().isNotFound());
    }
}
