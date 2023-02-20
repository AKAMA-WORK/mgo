package com.mgo.models;

import com.mgo.entities.Booking;
import com.mgo.entities.Bookingextraluggage;
import com.mgo.entities.Bookingluggage;
import com.mgo.entities.Bookingplace;

import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

public class BookingAdapter {
    private Integer idbooking;
    private String date;
    private String datepointage;
    private Integer amount;
    private String paymentid;
    private String paymentmethod;

    private String confirmdate;
    private String canceldate;
    private String description;
    private Long authorizedluggage;
    private String bookingluggageCollection;

    private String bookingstatus;
    private String client;
    private Integer msisdn;

    private String transferedfrom;
    private String departure;
    private String destination;
    private String departureDate;
    private String bookingplaceCollection;
    private String bookingextraluggageCollection;
    private String bookingcancelCollection;
    private Boolean boarding;
    private String boardingDate;
    private String category;

    public BookingAdapter() {

    }

    public BookingAdapter(Booking booking) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        this.setIdbooking(booking.getIdbooking());
        this.setCategory(booking.getDeparture().getCategory().getName());
        if (booking.getDate() != null)
            this.setDate(dateFormat.format(booking.getDate()));
        if (booking.getDatepointage() != null)
            this.setDatepointage(dateFormat.format(booking.getDatepointage()));
        this.setAmount(booking.getAmount());
        this.setPaymentid(booking.getPaymentid());
        if (booking.getPaymentmethod() != null)
            this.setPaymentmethod(booking.getPaymentmethod().getName());
        if (booking.getConfirmdate() != null)
            this.setConfirmdate(dateFormat.format(booking.getConfirmdate()));
        if (booking.getCanceldate() != null)
            this.setCanceldate(dateFormat.format(booking.getCanceldate()));
        this.setDescription(booking.getDescription());
        this.setAuthorizedluggage(booking.getAuthorizedluggage());
        if (booking.getBookingluggageCollection() != null && booking.getBookingluggageCollection().size() > 0)
            this.setBookingluggageCollection(booking.getBookingluggageCollection().stream().map(Bookingluggage::getDescription).collect(Collectors.joining(" / ")));
        if (booking.getBookingstatus() != null)
            this.setBookingstatus(booking.getBookingstatus().getName());
        this.setClient((booking.getClient().getFname() != null ?booking.getClient().getFname():"") + " " + (booking.getClient().getLname() != null ? booking.getClient().getLname() : ""));
        this.setMsisdn(booking.getClient().getPhone());
        if (booking.getTransferedfrom() != null)
            this.setTransferedfrom(booking.getTransferedfrom().getName());
        this.setDeparture(booking.getDeparture().getStartin().getName());
        this.setDestination(booking.getDeparture().getDestination().getName());
        this.setDepartureDate(dateFormat.format(booking.getDeparture().getDateheure()));
        if (booking.getBookingplaceCollection() != null && booking.getBookingplaceCollection().size() > 0)
            this.setBookingplaceCollection(booking.getBookingplaceCollection().stream().map(Bookingplace::getPlace)
                    .map(Object::toString).collect(Collectors.joining(" / ")));
        if (booking.getBookingextraluggageCollection() != null && booking.getBookingextraluggageCollection().size() > 0)
            this.setBookingextraluggageCollection(booking.getBookingextraluggageCollection().stream().map(Bookingextraluggage::getWeight).reduce(0L, Long::sum).toString());
        this.setBoarding(booking.getBoarding());
        if (booking.getBoardingDate() != null)
            this.setBoardingDate(dateFormat.format(booking.getBoardingDate()));
    }

    public Integer getIdbooking() {
        return idbooking;
    }

    public void setIdbooking(Integer idbooking) {
        this.idbooking = idbooking;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDatepointage() {
        return datepointage;
    }

    public void setDatepointage(String datepointage) {
        this.datepointage = datepointage;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }

    public String getConfirmdate() {
        return confirmdate;
    }

    public void setConfirmdate(String confirmdate) {
        this.confirmdate = confirmdate;
    }

    public String getCanceldate() {
        return canceldate;
    }

    public void setCanceldate(String canceldate) {
        this.canceldate = canceldate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAuthorizedluggage() {
        return authorizedluggage;
    }

    public void setAuthorizedluggage(Long authorizedluggage) {
        this.authorizedluggage = authorizedluggage;
    }

    public String getBookingstatus() {
        return bookingstatus;
    }

    public void setBookingstatus(String bookingstatus) {
        this.bookingstatus = bookingstatus;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getTransferedfrom() {
        return transferedfrom;
    }

    public void setTransferedfrom(String transferedfrom) {
        this.transferedfrom = transferedfrom;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public String getBookingplaceCollection() {
        return bookingplaceCollection;
    }

    public void setBookingplaceCollection(String bookingplaceCollection) {
        this.bookingplaceCollection = bookingplaceCollection;
    }

    public String getBookingextraluggageCollection() {
        return bookingextraluggageCollection;
    }

    public void setBookingextraluggageCollection(String bookingextraluggageCollection) {
        this.bookingextraluggageCollection = bookingextraluggageCollection;
    }

    public String getBookingluggageCollection() {
        return bookingluggageCollection;
    }

    public void setBookingluggageCollection(String bookingluggageCollection) {
        this.bookingluggageCollection = bookingluggageCollection;
    }

    public String getBookingcancelCollection() {
        return bookingcancelCollection;
    }

    public void setBookingcancelCollection(String bookingcancelCollection) {
        this.bookingcancelCollection = bookingcancelCollection;
    }

    public Boolean getBoarding() {
        return boarding;
    }

    public void setBoarding(Boolean boarding) {
        this.boarding = boarding;
    }

    public String getBoardingDate() {
        return boardingDate;
    }

    public void setBoardingDate(String boardingDate) {
        this.boardingDate = boardingDate;
    }

    public void setMsisdn(Integer msisdn) {
        this.msisdn = msisdn;
    }

    public Integer getMsisdn() {
        return msisdn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
