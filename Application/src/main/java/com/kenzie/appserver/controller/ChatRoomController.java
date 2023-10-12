package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ChatRoomCreateRequest;
import com.kenzie.appserver.controller.model.ChatRoomResponse;
import com.kenzie.appserver.controller.model.CommentResponse;
import com.kenzie.appserver.service.ChatRoomService;
import com.kenzie.appserver.service.CommentService;
import com.kenzie.appserver.service.model.ChatRoom;
import com.kenzie.appserver.service.model.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chatRoom")
public class ChatRoomController {
    private ChatRoomService chatRoomService;
    private CommentService commentService;

    ChatRoomController(ChatRoomService chatRoomService, CommentService commentService){
        this.chatRoomService = chatRoomService;
        this.commentService = commentService;
    }

   @PostMapping
    public ResponseEntity<ChatRoomResponse> chatRoom(
            @RequestBody ChatRoomCreateRequest chatRoomCreateRequest){
        // call the chatroomservice.sendcomment method with the commentmessafe and owneris from the reqquest.
        ChatRoom chatRooms = chatRoomService.sendComment(chatRoomCreateRequest.getSentComment(),
                chatRoomCreateRequest.getOwnerId());
                //then convert the resulting chatrooms into a chatroomresponse and return it.
        ChatRoomResponse chatRoomResponse = new ChatRoomResponse();
       chatRoomResponse.setSentComment(chatRooms.getSentComment());
       //chatRoomResponse.setTimestamp(chatRooms.getTimeStamp());
       chatRoomResponse.setOwnerId(chatRooms.getOwnerId());
       chatRoomResponse.setChatRoomId(chatRooms.getChatRoomId());

        return ResponseEntity.ok(chatRoomResponse);
    }
    @PostMapping("/chatrooms")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatRoom chatRoom){
        ChatRoom createdChatRoom = chatRoomService.createChatRoom(chatRoom);
        return new ResponseEntity<>(createdChatRoom, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChatRoomResponse>> getAllComments() {
        List<ChatRoom> chatRooms = chatRoomService.findAll();
        List<ChatRoomResponse> responses = chatRooms.stream().map(this::chatRoomResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }


    private ChatRoomResponse chatRoomResponse(ChatRoom chatRoom) {
        ChatRoomResponse chatRoomResponse = new ChatRoomResponse();
        chatRoomResponse.setSentComment(chatRoom.getSentComment());
        //chatRoomResponse.setTimestamp(chatRoom.getTimeStamp());
        chatRoomResponse.setOwnerId(chatRoom.getOwnerId());
        chatRoomResponse.setChatRoomId(chatRoom.getChatRoomId());


        //chatRoomResponse.setTimestamp(chatRoom.getTimeStamp()); //long versus string mismatch

        return chatRoomResponse;


    }

}
