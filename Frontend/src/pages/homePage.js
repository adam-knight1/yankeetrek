class HomePage extends BaseClass {
    constructor() {
        super();
    }

    async mount() {
        const chatroomsButton = document.createElement('button');
        chatroomsButton.innerText = "View Chatrooms";
        chatroomsButton.addEventListener('click', () => {
            // Navigate to Chatrooms Page
            window.location.href = '/path/to/chatrooms/page';
        });

        const container = document.getElementById("container");
        container.appendChild(chatroomsButton);
    }
}

const main = async () => {
    const homePage = new HomePage();
    homePage.mount();
};

window.addEventListener('DOMContentLoaded', main);
