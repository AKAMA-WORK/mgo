package com.mgo.socketio.selectseats;

import com.mgo.socketio.MessageListener;
import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import io.socket.socketio.server.SocketIoSocket;
import io.vertx.core.json.JsonObject;

public class SelectSeatsMessageListener  implements MessageListener<SelectSeatsMessageData> {

    public static final String MESSAGE_TYPE="SELECT_SEATS";
    @Override
    public String getType() {
        return MESSAGE_TYPE;
    }

    @Override
    public Class<SelectSeatsMessageData> getDataType() {
        return SelectSeatsMessageData.class;
    }

    @Override
    public void listen(SocketIoServer server, SocketIoSocket socket, SelectSeatsMessageData data) {
        SocketIoNamespace ns = server.namespace("/");
        JsonObject message = new JsonObject()
                .put("type",MESSAGE_TYPE)
                        .put("data",data);

        ns.broadcast(null,"message", message.encode());

    }
}
