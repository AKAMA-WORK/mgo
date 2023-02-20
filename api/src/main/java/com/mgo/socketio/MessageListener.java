package com.mgo.socketio;

import io.socket.socketio.server.SocketIoServer;
import io.socket.socketio.server.SocketIoSocket;

public interface MessageListener<D extends MessageData> {
    String getType();
    Class<D> getDataType();
    void listen(SocketIoServer server, SocketIoSocket socket, D data );
}
