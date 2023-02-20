package com.mgo.service.booking;

import java.util.Objects;

public class SeatAlreadyBooked extends RuntimeException {
    private String departureId;
    private Integer seatNumber;

    public SeatAlreadyBooked(String departureId, Integer seatNumber) {
        super("Seat "+seatNumber+" in departure "+departureId+" is already booked");
        this.departureId = departureId;
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "SeatAlreadyBooked{" +
                "departureId=" + departureId +
                ", seatNumber=" + seatNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatAlreadyBooked that = (SeatAlreadyBooked) o;
        return Objects.equals(departureId, that.departureId) && Objects.equals(seatNumber, that.seatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureId, seatNumber);
    }
}
