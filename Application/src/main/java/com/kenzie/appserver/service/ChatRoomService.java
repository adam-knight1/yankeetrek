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
    public ChatRoom sendComment(Comment sentComment, String ownerId ){
        Comment comment = commentService.addNewComment(sentMessage);
        if (commentService.addNewComment(sentMessage)){//.getMessageSent should be here from the comment class. i need something to close out of the message was sent or not
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad request");
        }

        ChatRoomRecord chatRoomRecord = new ChatRoomRecord();
        chatRoomRecord.setOwnerId(ownerId);
        chatRoomRecord.setTopicName(chatRoomRecord.getTopicName());
        chatRoomRecord.setChatRoomId(chatRoomRecord.getChatRoomId());
        chatRoomRecord.setTimeStamp(chatRoomRecord.getTimeStamp());
        chatRoomRepository.save(chatRoomRecord);

        return new ChatRoom(chatRoomRecord.getOwnerId(), chatRoomRecord.getChatRoomId(), chatRoomRecord.getTimeStamp());
    }

    public List<ChatRoom> findAll(){
        List<ChatRoom> chatRooms = new ArrayList<>();
        chatRoomRepository.findAll()
                .forEach(chatRoomRecord -> chatRooms.add(new ChatRoom(chatRoomRecord.getOwnerId(), chatRoomRecord.getChatRoomId(), chatRoomRecord.getTimeStamp())));
        return chatRooms;
    }

}
