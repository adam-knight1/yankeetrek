package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ChatRoomRepository;
import com.kenzie.appserver.repositories.model.ChatRoomRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.eventstream.Message;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatRoomService extends ChatRoomRecord {
    private ChatRoomRepository chatRoomRepository;
    private final List<ChatRoomRecord> userIdlist;

    //dont think this is right...?? -alexis
    //do i need to make a message class tooo??? -alexis
    //seems like the easiest way to do this but is off.. -alexis
    private final List<Message> chatLog;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository, List<ChatRoomRecord> userIdlist){
        this.chatRoomRepository = chatRoomRepository;
        this.userIdlist = userIdlist;
        this.chatLog = new ArrayList<Message>();
    }

    public void addUserId (ChatRoomRecord userId){
        userIdlist.add(userId);
    }
    public void removeUserId (ChatRoomRecord userId){
        userIdlist.remove(userId);
    }

    public void sendMessage (ChatRoomRecord sender, String messageContent){
        Message message = new Message(sender, messageContent.getBytes());
        chatLog.add(message);
    }

    public List<Message> getChatLog(){
        return chatLog;
    }

}
