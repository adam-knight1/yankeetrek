package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ChatRoomRepository;
import com.kenzie.appserver.repositories.model.ChatRoomRecord;
import com.kenzie.appserver.service.model.ChatRoom;
import com.kenzie.appserver.service.model.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
    public class ChatRoomService {
    private ChatRoomRepository chatRoomRepository;
    private CommentService commentService;

    public ChatRoomService (ChatRoomRepository chatRoomRepository, CommentService commentService ){
        this.chatRoomRepository = chatRoomRepository;
        this.commentService = commentService;
    }

    //Do you all think "commentMessage" should be listed as Comment or String? -ALEXIS
    //Still working on this constructor


    public List<ChatRoom> findAll(){
        List<ChatRoom> chatRooms = new ArrayList<>();
        chatRoomRepository.findAll()
                .forEach(chatRoomRecord -> chatRooms.add(new ChatRoom(chatRoomRecord.getOwnerId(), chatRoomRecord.getChatRoomId(), chatRoomRecord.getTimeStamp())));
        return chatRooms;
    }

}
