package com.mgo.socketio;

public class MessageData {
    Integer personId;// If user is authenticated

    String clientType;// WEB | BACK_OFFICE | USSD | FACEBOOK_BOT

    String correlationId;// Session Id for example/ Phone number for USSD // etc ...

    String socketId;


    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getSocketId() {
        return socketId;
    }

    public void setSocketId(String socketId) {
        this.socketId = socketId;
    }
}
