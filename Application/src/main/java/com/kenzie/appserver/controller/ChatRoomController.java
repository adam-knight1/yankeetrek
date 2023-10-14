package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ChatRoomCreateRequest;
import com.kenzie.appserver.controller.model.ChatRoomResponse;
import com.kenzie.appserver.controller.model.CommentCreateRequest;
import com.kenzie.appserver.controller.model.CommentResponse;
import com.kenzie.appserver.service.ChatRoomService;
import com.kenzie.appserver.service.CommentService;
import com.kenzie.appserver.service.model.ChatRoom;
import com.kenzie.appserver.service.model.Comment;
import com.kenzie.appserver.service.model.User;
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
    //WHICH IS BETTER? createchatroom1 or createchatroom2?
    //this is option 1
    @PostMapping("/chatrooms")
    public ResponseEntity<ChatRoom> createChatRoom1(@RequestBody ChatRoom chatRoom){
        ChatRoom createdChatRoom = chatRoomService.createChatRoom(chatRoom);
        return new ResponseEntity<>(createdChatRoom, HttpStatus.CREATED);
    }

  //this is option 2
    @PostMapping("/chatrooms")
    public ResponseEntity<ChatRoomResponse> createChatRoom2(@RequestBody ChatRoomCreateRequest chatRoomCreateRequest){
        ChatRoom chatRoom = new ChatRoom(
                chatRoomCreateRequest.getOwnerId(),
                chatRoomCreateRequest.getChatRoomId(),
                chatRoomCreateRequest.getTimeStamp(),
                chatRoomCreateRequest.getSentComment()
        );
        try {
            chatRoomService.createChatRoom(chatRoom);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(chatRoomResponse(chatRoom));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChatRoomResponse>> getAllChatRooms() {
        List<ChatRoom> chatRooms = chatRoomService.findAll();
        List<ChatRoomResponse> responses = chatRooms.stream().map(this::chatRoomResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

  /* @PutMapping("/chatrooms/{chatRoomId}")
    public ResponseEntity<ChatRoomResponse> updateChatRoomById (@PathVariable ("ChatRoomId") String chatRoomId, @RequestBody
                                                            ChatRoom updatedChatRoom){
        if (chatRoomService.chatRoomExists(chatRoomId)){
            chatRoomService.updateChatRoom
        }
    }*/

    @GetMapping("/chatrooms/{chatRoomId}")
    public ResponseEntity<ChatRoom> getChatRoomById(@PathVariable ("chatRoomId") String chatRoomId){
    ChatRoom chatRoom = chatRoomService.getChatRoomById(chatRoomId);

    if (chatRoom != null){
        return ResponseEntity.ok(chatRoom);
    }else{
        return ResponseEntity.notFound().build();
    }
}


    private ChatRoomResponse chatRoomResponse(ChatRoom chatRoom) {
        ChatRoomResponse chatRoomResponse = new ChatRoomResponse();
      //  chatRoomResponse.setSentComment(chatRoom.getSentComment()); having trouble getting this working
        chatRoomResponse.setTimestamp(chatRoom.getTimeStamp());
        chatRoomResponse.setOwnerId(chatRoom.getOwnerId());
        chatRoomResponse.setChatRoomId(chatRoom.getChatRoomId());
        return chatRoomResponse;


    }

}
