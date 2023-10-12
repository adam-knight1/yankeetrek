import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import UserClient from "../api/UserClient";

class UserPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onCreate', 'onUpdate', 'onDelete', 'onFind', 'renderUser'], this);
        this.dataStore = new DataStore();
    }

    async mount() {
        document.getElementById('create-user').addEventListener('submit', this.onCreate);
        document.getElementById('update-user').addEventListener('submit', this.onUpdate);
        document.getElementById('delete-user').addEventListener('submit', this.onDelete);
        document.getElementById('find-user').addEventListener('submit', this.onFind);
        this.client = new UserClient();

        this.dataStore.addChangeListener(this.renderUser)
    }

    async renderUser() {
    #merge conflict

         /*   let resultArea = document.getElementById("result-info");

            const example = this.dataStore.get("user");

            if (example) {
                resultArea.innerHTML = `
                    <div>ID: ${example.id}</div>
                    <div>Name: ${example.name}</div>
                `
            } else {
                resultArea.innerHTML = "No Item";
            }

        //update the ui here - adam*/
    }

    async onCreate(event) {
        event.preventDefault();

        this.dataStore.set("user", null);

        let username = document.getElementById("username-field").value;
        let email = document.getElementById("email-field").value;
        let password = document.getElementById("password-field").value;

        let createdUser = await this.client.createUser(username, email, password, this.errorHandler);

        this.dataStore.set("user", createdUser);

        if (createdUser) {
            this.showMessage(`User ${createdUser.username} created successfully!`);
        } else {
            this.errorHandler("Error creating user! Try again...");
        }
    }


    async onUpdate(event) {
        event.preventDefault();

        let userId = document.getElementById("update-user-id-field").value;

        await this.client.updateUser(userId, this.errorHandler);

    }

    async onDelete(event) {
        event.preventDefault();

        let userId = document.getElementById("delete-user-record-field").value;

        await this.client.deleteUser(userId, this.errorHandler);
                //update the ui here - adam

    }

    async onFind(event) {
        event.preventDefault();

        let userId = document.getElementById("find-user-id-field").value;

        const foundUser = await this.client.getUser(userId, this.errorHandler);
        this.dataStore.set("user", foundUser);


    }
}

const main = async () => {
    const userPage = new UserPage();
    userPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
