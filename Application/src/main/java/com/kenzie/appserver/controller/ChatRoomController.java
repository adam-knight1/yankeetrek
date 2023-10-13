package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ChatRoomCreateRequest;
import com.kenzie.appserver.controller.model.ChatRoomResponse;
import com.kenzie.appserver.controller.model.CommentCreateRequest;
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

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/chatRoom")
public class ChatRoomController {
    private ChatRoomService chatRoomService;
    private CommentService commentService;

    ChatRoomController(ChatRoomService chatRoomService, CommentService commentService){
        this.chatRoomService = chatRoomService;
        this.commentService = commentService;
    }

   @PostMapping("/chatrooms/{chatRoomId}/comments")
    public ResponseEntity<String> sendComment(
            @PathVariable String chatRoomId, @RequestBody CommentCreateRequest commentCreateRequest){
       Comment comment = new Comment(
               randomUUID().toString(),
               commentCreateRequest.getOwnerId(),
               commentCreateRequest.getTitle(),
               commentCreateRequest.getContent(),
               commentCreateRequest.getChatRoomId()
       );
       try {
           chatRoomService.sendComment(comment, chatRoomId);
       }catch (IllegalArgumentException e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
       return ResponseEntity.ok("Message sent successfully");
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

 /*   @PutMapping("/chatrooms/{chatRoomId}")
    public ResponseEntity<ChatRoomResponse> updateChatRoom (@PathVariable ("ChatRoomId") String chatRoomId, @RequestBody
                                                            ChatRoomCreateRequest request){
        List<ChatRoom> updatedChatRoom  = chatRoomService.
    }*/


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
