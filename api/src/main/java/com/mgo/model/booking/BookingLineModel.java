package com.mgo.model.booking;


import com.mgo.entity.BookingLine;
import com.mgo.model.departure.DepartureModel;
import com.mgo.service.departure.DepartureService;
import com.mgo.util.DateTimeFormat;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Schema(name = "BookingLine")
public class BookingLineModel {

    @Schema(name = "bookingLineId", description = "The id")
     String bookingLineId;

    @Schema(name = "price", description = "The price")
    Double price;
    @Schema(name = "amount", description = "The amount")
    Double amount;

    @Schema(name = "description", description = "The description")
     String description;

    @Schema(name = "status", description = "The status",enumeration = {
            BookingStatus.INITIATED,BookingStatus.CONFIRMED, BookingStatus.CANCELLED
    })
    String status;

    @Schema(name = "departure", description = "The departure", ref = "Departure")
    DepartureModel departure;


    @Schema(name = "boardingDate", description = "The  boarding date")
     String boardingDate;

    @Schema(name = "cancellation", description = "The cancellation", ref = "BookingCancellation")
    BookingCancellationModel cancellation;

    @Schema(name = "luggages", description = "The luggages", ref = "BookingLineLuggage")
    List<BookingLineLuggageModel> luggages;

    @Schema(name = "extraLuggages", description = "The extraLuggages", ref = "BookingLineExtraLuggage")
    List<BookingLineExtraLuggageModel> extraLuggages;

    @Schema(name = "seats", description = "The seats", ref = "BookingLineSeat")
    List<BookingLineSeatModel> seats;

    public BookingLineModel() {
    }


    public BookingLineModel(BookingLine bookingLine, Date now) {
        this.setBookingLineId(bookingLine.getBookingLineId());
        this.setAmount(bookingLine.getAmount());
        this.setDescription(bookingLine.getDescription());
        this.setStatus(bookingLine.getStatus());
        this.setBoardingDate( bookingLine.getBoardingDate()!=null? DateTimeFormat.formatDatetime(bookingLine.getBoardingDate()):null);
        this.setCancellation(bookingLine.getCancellation()!=null? new BookingCancellationModel(bookingLine.getCancellation()) : null);
        this.setPrice(bookingLine.getPrice());
        this.setDeparture(new DepartureModel(bookingLine.getDeparture(), DepartureService.findDepartureOrganizationConfig(bookingLine.getDeparture()),now));

        if(bookingLine.getLuggages()!=null){
            this.setLuggages(bookingLine.getLuggages().stream().map(BookingLineLuggageModel::new).collect(Collectors.toList()));
        }

        if(bookingLine.getExtraLuggages()!=null){
            this.setExtraLuggages(bookingLine.getExtraLuggages().stream().map(BookingLineExtraLuggageModel::new).collect(Collectors.toList()));
        }

        if(bookingLine.getSeats()!=null){
            this.setSeats(bookingLine.getSeats().stream().map(BookingLineSeatModel::new).collect(Collectors.toList()));
        }
    }

    public String getBookingLineId() {
        return bookingLineId;
    }

    public void setBookingLineId(String bookingLineId) {
        this.bookingLineId = bookingLineId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DepartureModel getDeparture() {
        return departure;
    }

    public void setDeparture(DepartureModel departure) {
        this.departure = departure;
    }

    public String getBoardingDate() {
        return boardingDate;
    }

    public void setBoardingDate(String boardingDate) {
        this.boardingDate = boardingDate;
    }

    public BookingCancellationModel getCancellation() {
        return cancellation;
    }

    public void setCancellation(BookingCancellationModel cancellation) {
        this.cancellation = cancellation;
    }

    public List<BookingLineLuggageModel> getLuggages() {
        return luggages;
    }

    public void setLuggages(List<BookingLineLuggageModel> luggages) {
        this.luggages = luggages;
    }

    public List<BookingLineExtraLuggageModel> getExtraLuggages() {
        return extraLuggages;
    }

    public void setExtraLuggages(List<BookingLineExtraLuggageModel> extraLuggages) {
        this.extraLuggages = extraLuggages;
    }

    public List<BookingLineSeatModel> getSeats() {
        return seats;
    }

    public void setSeats(List<BookingLineSeatModel> seats) {
        this.seats = seats;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}