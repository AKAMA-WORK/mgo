package com.mgo.socketio;

import com.mgo.socketio.selectseats.SelectSeatsMessageListener;
import io.quarkus.runtime.StartupEvent;
import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import io.socket.socketio.server.SocketIoSocket;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class SocketIoApplication {
    @ConfigProperty(name = "socketio.hostname")
    String hostname;

    @ConfigProperty(name = "socketio.port")
    Integer port;

    private  SocketIoServer server;





    public  void startup(@Observes StartupEvent event){
        System.out.println("Start Socket io Application "+this.hostname+":"+this.port);

        List<MessageListener> listeners = List.of(new SelectSeatsMessageListener());
        final ServerWrapper serverWrapper = new ServerWrapper(this.hostname, this.port, null); // null means "allow all" as stated in https://github.com/socketio/engine.io-server-java/blob/f8cd8fc96f5ee1a027d9b8d9748523e2f9a14d2a/engine.io-server/src/main/java/io/socket/engineio/server/EngineIoServerOptions.java#L26
        try {
            serverWrapper.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
         this.server = serverWrapper.getSocketIoServer();
        SocketIoNamespace ns = this.server.namespace("/");
        ns.on("connection", args -> {
            SocketIoSocket socket = (SocketIoSocket) args[0];

            socket.on("message", args1 -> {

                JsonObject messageAsJsonObject = new JsonObject(args1[0].toString());
                String type = messageAsJsonObject.getString("type");

                MessageListener listener = listeners.stream().filter(messageListener -> messageListener.getType().equals(type)).findFirst().orElse(null);
                if(listener!=null){
                     @SuppressWarnings("unchecked") Object data = messageAsJsonObject.getJsonObject("data").mapTo(listener.getDataType());
                      listener.listen(this.server, socket, (MessageData) data);
                }
                else{
                    System.out.println("Listener not found for "+type);
                }

              //  socket.send("message", "test message "+ Arrays.toString(args1), 1);
            });

        });
    }



   /* private void onSeatSelection(){
       // SocketIoNamespace ns = this.server.namespace("/");
    }

    public void notifySeatSelection(SeatSelectionModel selectionModel){
        SocketIoNamespace ns = this.server.namespace("/");
        ns.broadcast(null,"message", Json.encode(selectionModel));
    }*/

    public  SocketIoServer getSocketIoServer (){
        return server;
    }
}
