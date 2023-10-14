import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import ExampleClient from "../api/chatRoomClient";

class chatRoomsPage extends BaseClass {
    constructor() {
        super();
        this.bindClassMethods(['renderChatrooms', 'onGet', 'getChatRoomById', 'onDelete'], this);
        this.dataStore = new DataStore();
    }

    async mount() {
        this.client = new ChatRoomClient();
        this.dataStore.addChangeListener(this.renderChatrooms);

        // Fetch chatrooms
        const chatrooms = await this.client.getAllChatrooms();
        this.dataStore.set("chatrooms", chatrooms);
    }

    async renderChatrooms() {
        const chatrooms = this.dataStore.get("chatrooms");
        const list = document.getElementById("chatroom-list");

        chatrooms.forEach((chatroom) => {
            const listItem = document.createElement("li");
            listItem.innerText = chatroom.name;
            list.appendChild(listItem);
        });
    }

      async onGet(event) {
            // Prevent the page from refreshing on form submit
            event.preventDefault();

            let id = document.getElementById("id-field").value;
            this.dataStore.set("example", null);

            let result = await this.client.getExample(id, this.errorHandler);
            this.dataStore.set("example", result);
            if (result) {
                this.showMessage(`Got ${result.name}!`)
            } else {
                this.errorHandler("Error doing GET!  Try again...");
            }
        }

        async getChatRoomById(chatRoomId) {
          try {
            const response = await axios.get(`${apiUrl}/${chatRoomId}`);
            return response.data; // Assuming the API returns a JSON object for the chat room
          } catch (error) {
            console.error('Error fetching chat room:', error);
            throw error;
          }
        }

        async onDelete(chatRoomId) {
          try {
            const response = await axios.delete(`${apiUrl}/${chatRoomId}`);
            return response.data; // Assuming the API returns a response (e.g., success message)
          } catch (error) {
            console.error('Error deleting chat room:', error);
            throw error;
          }
        }

}
/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const chatroomsPage = new ChatroomsPage();
    chatroomsPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
