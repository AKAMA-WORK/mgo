package com.mgo.socketio.selectseats;


import com.mgo.socketio.MessageData;

import java.util.Collection;

public class SelectSeatsMessageData extends MessageData {

    Collection<SelectSeatsMessageDataSeat> seats;

    public Collection<SelectSeatsMessageDataSeat> getSeats() {
        return seats;
    }

    public void setSeats(Collection<SelectSeatsMessageDataSeat> seats) {
        this.seats = seats;
    }
}
