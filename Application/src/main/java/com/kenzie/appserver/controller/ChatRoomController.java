package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ChatRoomCreateRequest;
import com.kenzie.appserver.controller.model.ChatRoomResponse;
import com.kenzie.appserver.controller.model.CommentResponse;
import com.kenzie.appserver.service.ChatRoomService;
import com.kenzie.appserver.service.CommentService;
import com.kenzie.appserver.service.model.ChatRoom;
import com.kenzie.appserver.service.model.Comment;
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
    //@postmapping
//    @PostMapping
//    public ResponseEntity<ChatRoomResponse> chatRoom(
//            @RequestBody ChatRoomCreateRequest chatRoomCreateRequest){
//        // call the chatroomservice.sendcomment method with the commentmessafe and owneris from the reqquest.
//        ChatRoom chatRooms = chatRoomService.sendComment(chatRoomCreateRequest.getcommentMessage(), chatRoomCreateRequest
//                .getownerId());
//                //then convert the resulting chatrooms into a chatroomresponse and return it.
//        ChatRoomResponse chatRoomResponse = new ChatRoomResponse();
//        chatRoomResponse.setChatRoomId(chatRooms.getChatRoomId());
//        chatRoomResponse.setOwnerId(chatRooms.getUserId());
//        chatRoomResponse.setTimestamp(chatRooms.getTimeStamp());
//
//        return ResponseEntity.ok(chatRoomResponse);
//    }
    @GetMapping("/all")
    public ResponseEntity<List<ChatRoomResponse>> getAllComments() {
        List<ChatRoom> chatRooms = chatRoomService.findAll();
        List<ChatRoomResponse> responses = chatRooms.stream().map(this::chatRoomResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
    private ChatRoomResponse chatRoomResponse(ChatRoom chatRoom) {
        ChatRoomResponse chatRoomResponse = new ChatRoomResponse();
        chatRoomResponse.setChatRoomId(chatRoom.getChatRoomId());
        chatRoomResponse.setOwnerId(chatRoom.getUserId());
//        chatRoomResponse.setTimestamp(chatRoom.getTimeStamp());
        return chatRoomResponse;


    }
    //@getmapping
}
