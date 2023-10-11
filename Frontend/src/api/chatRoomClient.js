import BaseClass from "../util/baseClass";
import axios from 'axios'

/**
 * Client to call the MusicPlaylistService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
 */
export default class ChatRoomClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getChatRoom', 'createChatRoom', 'sendMessage'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    async getChatRoom(id, errorCallback) {
        try {
            const response = await this.client.get(`/chatroom/${id}`);
            return response.data;
        } catch (error) {
            this.handleError("getChatRoom", error, errorCallback)
        }
    }

    async createChatRoom(name, errorCallback) {
        try {
            const response = await this.client.post(`chatroom`, { "name" : name });
            return response.data;
        } catch (error) {
            this.handleError("createChatRoom", error, errorCallback);
        }
    }

    async sendMessage(message, chatRoomId, errorCallback) {
        try {
            const response = await this.client.post(`chatroom/${chatRoomId}/message`, { "content" : message });
            return response.data;
        } catch (error) {
            this.handleError("sendMessage", error, errorCallback);
        }
    }

    handleError(method, error, errorCallback) {
        console.error(`${method} failed - ${error}`);
        if (error.response && error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(`${method} failed - ${error}`);
        }
    }
}

