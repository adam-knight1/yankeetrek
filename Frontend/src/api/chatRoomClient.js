import BaseClass from "../util/baseClass";
import axios from 'axios'


export default class ChatRoomClient {
    constructor(baseURL) {
        this.client = axios.create({
            baseURL: baseURL
        });
    }


    async createChatRoom(ownerId, chatRoomId) {
        try {
            const response = await this.client.post('/chatroom', {
            ownerId: ownerId,
            chatRoomId: chatRoomId
            });
            return response.data;
        } catch (error) {
        console.error('createChatRoom', error);
        throw error;
        }
    }
//       handleError(method, error, errorCallback) {
//            console.error(method + " failed - " + error);
//            if (error.response.data.message !== undefined) {
//                console.error(error.response.data.message);
//            }
//            if (errorCallback) {
//                errorCallback(method + " failed - " + error);
//            }
//        }


}

