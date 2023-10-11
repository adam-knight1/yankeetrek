class chatRoomsPage extends BaseClass {
    constructor() {
        super();
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
}

const main = async () => {
    const chatroomsPage = new ChatroomsPage();
    chatroomsPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
