package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ChatRoomRepository;
import com.kenzie.appserver.repositories.model.ChatRoomRecord;
import com.kenzie.appserver.repositories.model.CommentRecord;
import com.kenzie.appserver.service.model.ChatRoom;
import com.kenzie.appserver.service.model.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
    public class ChatRoomService {
    private ChatRoomRepository chatRoomRepository;
    private CommentService commentService;
    private List<ChatRoom> chatRooms;
    private UserService userService;

    public ChatRoomService (ChatRoomRepository chatRoomRepository, CommentService commentService, UserService userService ){
        this.chatRoomRepository = chatRoomRepository;
        this.commentService = commentService;
        this.userService = userService;
        this.chatRooms = new ArrayList<>();
    }

    public ChatRoom sendComment(Comment sentComment, String ownerId ){
        Comment comment = commentService.addNewComment(sentComment);
        ChatRoomRecord chatRoomRecord = new ChatRoomRecord();
        chatRoomRecord.setOwnerId(ownerId);
        chatRoomRecord.setTopicName(chatRoomRecord.getTopicName());
        //do i need another .setcomment? example from unit 4 project
        chatRoomRecord.setChatRoomId(chatRoomRecord.getChatRoomId());
        chatRoomRecord.setTimeStamp(chatRoomRecord.getTimeStamp());
        chatRoomRepository.save(chatRoomRecord);


        return new ChatRoom(chatRoomRecord.getOwnerId(), chatRoomRecord.getChatRoomId(), chatRoomRecord.getTimeStamp(),
                chatRoomRecord.getComment());

    }
    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        chatRooms.add(chatRoom);
        return chatRoom;
    }

    //tests work
    public List<ChatRoom> findAll(){
        List<ChatRoom> chatRooms = new ArrayList<>();
        chatRoomRepository
                .findAll()
                .forEach(chatRoom -> chatRooms.add(new ChatRoom(chatRoom.getOwnerId(), chatRoom.getChatRoomId(),
                        chatRoom.getTimeStamp(), chatRoom.getComment())));
        return chatRooms;
    }


    public ChatRoom getChatRoomById(String chatRoomId){
        for (ChatRoom chatRoom : chatRooms){
            if (chatRoom.getChatRoomId().equals(chatRoomId))
                return chatRoom;
        }
        return null;//return null if the chat room is not found or do we want an exception?
    }
    public void deleteChatRoom (ChatRoom chatRoom){
        chatRooms.remove(chatRoom);
    }

}
