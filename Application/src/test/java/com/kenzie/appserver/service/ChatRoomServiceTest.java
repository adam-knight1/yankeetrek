package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ChatRoomRepository;
import com.kenzie.appserver.repositories.ExampleRepository;
import com.kenzie.appserver.repositories.model.ChatRoomRecord;
import com.kenzie.appserver.repositories.model.ExampleRecord;
import com.kenzie.appserver.service.model.ChatRoom;
import com.kenzie.appserver.service.model.Example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChatRoomServiceTest {
    private ChatRoomRepository chatRoomRepository;
    private ChatRoomService chatRoomService;
    private CommentService commentService;
    private UserService userService;

    @BeforeEach
    void setup() {
        chatRoomRepository = mock(ChatRoomRepository.class);
        commentService = mock(CommentService.class);
        userService = mock(UserService.class);
        chatRoomService = new ChatRoomService(chatRoomRepository,commentService, userService);
    }
    /** ------------------------------------------------------------------------
     *  CHATROOMSERVICE.FINDBYID
     *  ------------------------------------------------------------------------ **/

    @Test
    void findById() {
        // GIVEN
      //  String id = randomUUID().toString();

       // ChatRoomRecord record = new ChatRoomRecord();
      //  record.setChatRoomId();
      //  record.setId(id);
      //  record.setName("concertname");

        // WHEN
    //    when(chatRoomRepository.findById(id)).thenReturn(Optional.of(record));
       // ChatRoomService chatRooms = chatRoomService.findAll();

        // THEN
     //   Assertions.assertNotNull(example, "The object is returned");
   //     Assertions.assertEquals(record.getId(), example.getId(), "The id matches");
     //   Assertions.assertEquals(record.getName(), example.getName(), "The name matches");
    }

    @Test
    void findByConcertId_invalid() {
        // GIVEN
     //   String id = randomUUID().toString();

    //    when(exampleRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN
    //    Example example = exampleService.findById(id);

        // THEN
    //    Assertions.assertNull(example, "The example is null when not found");
    }

}
