package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ChatRoomRepository;
import com.kenzie.appserver.repositories.model.ChatRoomRecord;
import com.kenzie.appserver.service.model.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.eventstream.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
    public class ChatRoomService {
    private ChatRoomRepository chatRoomRepository;
   // private final List<ChatRoomRecord> userIdlist;

    //dont think this is right...?? -alexis
    //do i need to make a message class tooo??? -alexis
    //seems like the easiest way to do this but is off.. -alexis
   // private final List<Message> chatLog;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository){
        this.chatRoomRepository = chatRoomRepository;
      //  this.userIdlist = userIdlist;
      //  this.chatLog = new ArrayList<Message>();
    }

    public ChatRoom findById (String id){
        return chatRoomRepository
                .findById(id)
                .map(chatRoom -> new ChatRoom(chatRoom.getPersonId(), chatRoom.getMessage(), chatRoom.getTopicName(),
                        chatRoom.getChatRoomId(), chatRoom.getTimeStamp(), chatRoom.getCommentList()))
                .orElse(null);
    }
    //  public void addUserId (ChatRoomRecord userId){
    //    userIdlist.add(userId);
    // }
    // public void removeUserId (ChatRoomRecord userId){
    //   userIdlist.remove(userId);
    // }

    //public void sendMessage (ChatRoomRecord sender, String messageContent){
    //    Message message = new Message(sender, messageContent.getBytes());
    //    chatLog.add(message);
    // }

    // public List<Message> getChatLog(){
    //    return chatLog;
    //}

}
