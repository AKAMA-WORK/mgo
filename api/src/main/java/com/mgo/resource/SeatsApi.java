package com.mgo.resource;

import com.mgo.socketio.SocketIoApplication;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("/seats")
@Tag(name = "Seats API")
public class SeatsApi {

    @Inject
    SocketIoApplication socketIoApplication;



    public void listenSocket(){


    }

}
