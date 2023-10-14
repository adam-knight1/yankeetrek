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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChatRoomServiceTest {
  private ChatRoomService chatRoomService;

  @BeforeEach
    void setup(){
      chatRoomService = mock(ChatRoomService.class);
  }
    @Test
    public void findAll_Successful() {
        // Given
        String chatRoomid = randomUUID().toString();
        String chatRoomid2 = randomUUID().toString();

        ChatRoom chatRoom = new ChatRoom(null,chatRoomid,null,null);
        ChatRoom chatRoom2 = new ChatRoom(null,chatRoomid2,null,null);

        List<ChatRoom> chatRoomList = new ArrayList<>();
        chatRoomList.add(chatRoom);
        chatRoomList.add(chatRoom2);

        // When
        when(chatRoomService.findAll()).thenReturn(chatRoomList);
        List<ChatRoom> results = chatRoomService.findAll();

        // Then
        Assertions.assertEquals(results, chatRoomList, "Chat Room lists should match");
    }

    @Test
    public void findAll_NoChatRooms_ReturnsNull() {
        // Given/When
        when(chatRoomService.findAll()).thenReturn(null);
        List<ChatRoom> results = chatRoomService.findAll();

        // Then
        Assertions.assertNull(results);
    }

}
