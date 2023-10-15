package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ChatRoomRepository;
import com.kenzie.appserver.repositories.CommentRepository;
import com.kenzie.appserver.repositories.ExampleRepository;
import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.ChatRoomRecord;
import com.kenzie.appserver.repositories.model.ExampleRecord;
import com.kenzie.appserver.service.model.ChatRoom;
import com.kenzie.appserver.service.model.Comment;
import com.kenzie.appserver.service.model.Example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//@SpringBootTest
//public class ChatRoomServiceTest {
//
//  private ChatRoomService chatRoomService;
//
//  private ChatRoomRepository chatRoomRepository;
//
//  private CommentService commentService;
//
//  @BeforeEach
//    void setup(){
//      chatRoomRepository = mock(ChatRoomRepository.class);
//      commentService = mock(CommentService.class);
//      chatRoomService = new ChatRoomService(chatRoomRepository,commentService);
//  }
//    @Test
//    public void findAll_Successful() {
//        // Given
//        String chatRoomid = randomUUID().toString();
//        String chatRoomid2 = randomUUID().toString();
//
//        // Call the findAll method of chatRoomService
//        List<ChatRoom> chatRooms = chatRoomService.findAll();
//
//        // Perform assertions to check the results
//
//        // Assuming you have expected test data, e.g., two chat rooms
//        ChatRoom expectedChatRoom1 = new ChatRoom(null, chatRoomid, null, null);
//        ChatRoom expectedChatRoom2 = new ChatRoom(null, chatRoomid2, null, null);
//
//        assertEquals(2, chatRooms.size(), "There should be two chat rooms in the result");
//        Assertions.assertTrue(chatRooms.contains(expectedChatRoom1), "Chat Room 1 should be in the result");
//        Assertions.assertTrue(chatRooms.contains(expectedChatRoom2), "Chat Room 2 should be in the result");
//    }
//
//
//    @Test
//    public void findAll_NoChatRooms_ReturnsNull() {
//        // Given/When
//        when(chatRoomService.findAll()).thenReturn(null);
//        List<ChatRoom> results = chatRoomService.findAll();
//
//        // Then
//        Assertions.assertNull(results);
//    }
//    @Test
//    public void testSendComment() {
//        // Create a new Comment
//        Comment comment = new Comment();
//
//        // Call the sendComment method
//        ChatRoom createdChatRoom = chatRoomService.sendComment(comment, "ownerId");
//
//        // Perform assertions to check if the comment was sent successfully
//        assertNotNull(createdChatRoom);
//        assertNotNull(createdChatRoom.getChatRoomId());
//
//        // You can also fetch the ChatRoom from the repository and compare it with the createdChatRoom
//        ChatRoomRecord retrievedChatRoom = chatRoomRepository.findById(createdChatRoom.getChatRoomId()).orElse(null);
//        assertNotNull(retrievedChatRoom);
//        assertEquals("ownerId", retrievedChatRoom.getOwnerId());
//    }
//
//    @Test
//    public void testCreateChatRoom() {
//
//        String chatRoomid = randomUUID().toString();
//        // Create a ChatRoom
//        ChatRoom chatRoom = new ChatRoom(null, chatRoomid, null, null);
//
//        // Call the createChatRoom method
//        ChatRoom createdChatRoom = chatRoomService.createChatRoom(chatRoom);
//
//        // Perform assertions to check if the chat room was created successfully
//        assertNotNull(createdChatRoom);
//        assertNotNull(createdChatRoom.getChatRoomId());
//
//        // You can also fetch the ChatRoom from the repository and compare it with the createdChatRoom
//        ChatRoomRecord retrievedChatRoom = chatRoomRepository.findById(createdChatRoom.getChatRoomId()).orElse(null);
//        assertNotNull(retrievedChatRoom);
//        assertEquals("Test Chat Room", retrievedChatRoom.getTopicName());
//    }

}
