import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

const SOCKET_URL = 'http://localhost:8080/socket';

const connect = (onMessageReceived) => {
    console.log("In Connect");
    const client = new Client({
        webSocketFactory: () => new SockJS(SOCKET_URL),
        reconnectDelay: 5000,
        onConnect: () => {
            console.log('Connected');
            client.subscribe('/topic/socket/product', onMessageReceived);
        },
        onStompError: (frame) => {
            console.error('Broker reported error: ' + frame.headers['message']);
            console.error('Additional details: ' + frame.body);
        },
    });
    client.activate();
    return client;
};

export { connect };
