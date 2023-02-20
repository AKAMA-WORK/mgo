package com.mgo.model.booking;


import com.mgo.entity.BookingLineSeat;
import org.eclipse.microprofile.openapi.annotations.media.Schema;



@Schema(name = "BookingLineSeat")
public class BookingLineSeatModel {

    @Schema(name = "bookingLineSeatId", description = "The id")
     String bookingLineSeatId;

    @Schema(name = "seatNumber", description = "The seat number")
     Integer seatNumber;

    @Schema(name = "status", description = "The status",enumeration = {
            BookingLineSeatStatus.LOCKED, BookingLineSeatStatus.BOOKED, BookingLineSeatStatus.CANCELLED
    })
     String status;

    @Schema(name = "cancellation", description = "The cancellation", ref = "BookingCancellation")
    BookingCancellationModel cancellation;

    public BookingLineSeatModel() {
    }

    public BookingLineSeatModel(BookingLineSeat lineSeat) {
        this.setBookingLineSeatId(lineSeat.getBookingLineSeatId());
        this.setSeatNumber(lineSeat.getSeatNumber());
        this.setStatus(lineSeat.getStatus());
        if(cancellation!=null){
            this.setCancellation(new BookingCancellationModel(lineSeat.getCancellation()));
        }
    }

    public String getBookingLineSeatId() {
        return bookingLineSeatId;
    }

    public void setBookingLineSeatId(String bookingLineSeatId) {
        this.bookingLineSeatId = bookingLineSeatId;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BookingCancellationModel getCancellation() {
        return cancellation;
    }

    public void setCancellation(BookingCancellationModel cancellation) {
        this.cancellation = cancellation;
    }
}