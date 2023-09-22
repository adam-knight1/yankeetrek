package com.kenzie.appserver.service.model;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    //added by adam

    String chatRoomId;
    String topicName;
    List<Comment> commentList = new ArrayList<>();
   // List<String> userList = new ArrayList<>();
            //create hobby class?

    public void ChatRoom(String chatRoomId,String topicName,List<Comment> commentList){
        this.chatRoomId = chatRoomId;
        this.topicName = topicName;
        this.commentList = commentList;

    }


}
