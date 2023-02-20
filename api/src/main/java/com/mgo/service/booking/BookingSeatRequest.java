package com.mgo.service.booking;

public class BookingSeatRequest {
    private Integer seatNumber;
    private String departureId;

    private String clientIdPerson;


    public BookingSeatRequest(Integer seatNumber, String departureId, String clientIdPerson) {
        this.seatNumber = seatNumber;
        this.departureId = departureId;
        this.clientIdPerson = clientIdPerson;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getDepartureId() {
        return departureId;
    }

    public void setDepartureId(String departureId) {
        this.departureId = departureId;
    }

    public String getClientIdPerson() {
        return clientIdPerson;
    }

    public void setClientIdPerson(String clientIdPerson) {
        this.clientIdPerson = clientIdPerson;
    }
}
