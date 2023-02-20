package com.mgo.model.booking;


import com.mgo.entity.BookingLineLuggage;
import com.mgo.util.DateTimeFormat;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(name = "BookingLineLuggage")
public class BookingLineLuggageModel {

    @Schema(name = "bookingLineLuggageId", description = "The id")
     String bookingLineLuggageId;

    @Schema(name = "weight", description = "The weight")
     Double weight;

    @Schema(name = "date", description = "The date")
     String date;

    @Schema(name = "description", description = "The description/comment")
     String description;

    public BookingLineLuggageModel() {
    }

    public BookingLineLuggageModel(BookingLineLuggage lineLuggage) {
        this.setBookingLineLuggageId(lineLuggage.getBookingLineLuggageId());
        this.setWeight(lineLuggage.getWeight());
        this.setDate(DateTimeFormat.formatDatetime(lineLuggage.getDate()));
        this.setDescription(lineLuggage.getDescription());
    }

    public String getBookingLineLuggageId() {
        return bookingLineLuggageId;
    }

    public void setBookingLineLuggageId(String bookingLineLuggageId) {
        this.bookingLineLuggageId = bookingLineLuggageId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}