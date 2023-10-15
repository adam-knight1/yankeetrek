package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ChatRoomRepository;
import com.kenzie.appserver.repositories.model.ChatRoomRecord;
import com.kenzie.appserver.service.model.ChatRoom;
import com.kenzie.appserver.service.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
    public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final CommentService commentService;
    private final List<ChatRoom> chatRooms;
    //private  UserService userService;
    @Autowired
    public ChatRoomService (ChatRoomRepository chatRoomRepository, CommentService commentService ){
        this.chatRoomRepository = chatRoomRepository;
        this.commentService = commentService;
       // this.userService = userService;
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


        return new ChatRoom(chatRoomRecord.getOwnerId(), chatRoomRecord.getChatRoomId());

    }
    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        ChatRoom chatRoom1 = new ChatRoom(chatRoom.getOwnerId(), chatRoom.getChatRoomId());
        chatRooms.add(chatRoom1);
        return chatRoom1;
    }

    //tests work
    public List<ChatRoom> findAll(){
        List<ChatRoom> chatRooms = new ArrayList<>();
        chatRoomRepository
                .findAll()
                .forEach(chatRoom -> chatRooms.add(new ChatRoom(chatRoom.getOwnerId(), chatRoom.getChatRoomId())));
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
