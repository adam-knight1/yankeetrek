import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import ChatRoomClient from "../api/chatRoomClient";
import axios from 'axios';

class ChatRoomsPage extends BaseClass {
    constructor() {
        super();
        this.bindClassMethods(['onCreate'], this);
        this.dataStore = new DataStore();
    }

      async mount() {
           document.getElementById('create-chatRoom').addEventListener('submit', this.onCreate);
           this.client = new ChatRoomClient();
          this.dataStore.addChangeListener(this.renderChatrooms);
       }

    async renderChatrooms() {

    }

   async onCreate(event) {
           event.preventDefault();


          this.dataStore.set("chatRoom", null);

           let ownerId = document.getElementById("ownerId-field").value;
           let chatRoomId = document.getElementById("chatRoomId-field").value;
        try {
        let createdChatRoom = await this.client.createChatRoom(ownerId, chatRoomId, this.errorHandler);

        this.dataStore.set("chatRoom", createdChatRoom);

        if (createdChatRoom) {
            this.showMessage(`ChatRoom ${createdChatRoom.ownerId} created successfully!`);
        } else {
            this.errorHandler("Error creating chat room! Try again...");
        }
          } catch (error) {
        this.errorHandler(error);
    }
    }
}


const main = async () => {
    const chatRoomsPage = new ChatRoomsPage();
    chatRoomsPage.mount();
};

window.addEventListener('DOMContentLoaded', main);