package com.mgo.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Entity
@Table(name = "booking_line")
public class BookingLine extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_booking_line")
    private String bookingLineId;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date creationDate;


    @JoinColumn(name = "creation_id_person", referencedColumnName = "id_person")
    @ManyToOne
    private Person createdBy;


    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedDate;

    @JoinColumn(name = "update_id_person", referencedColumnName = "id_person")
    @ManyToOne
    private Person updatedBy;

    @Column(name = "creation_client_id")
    private String createdClientId;

    @Column(name = "update_client_id")
    private String updateClientId;


    @JoinColumn(name = "id_booking", referencedColumnName = "id_booking")
    @ManyToOne
    private Booking booking;

    @Column(name = "check_in_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkInDate;


    @Column(name = "price",precision = 2)
    private Double price;


    @Column(name = "amount",precision = 2)
    private Double amount;


    @Lob
    @Column(name = "description",columnDefinition = "text")
    private String description;

    @Column(name = "status",length = 50)
    private String status;



    @JoinColumn(name = "transferred_from_id_organization", referencedColumnName = "id_organization")
    @ManyToOne
    private Organization transferredFrom;

    @JoinColumn(name = "id_departure", referencedColumnName = "id_departure")
    @ManyToOne
    private Departure departure;

    @Column(name = "boarding_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date boardingDate;

    @JoinColumn(name = "id_booking_cancellation", referencedColumnName = "id_booking_cancellation")
    @ManyToOne
    private BookingCancellation cancellation;


    @OneToMany(mappedBy = "bookingLine")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<BookingLineLuggage> luggages;


    @OneToMany(mappedBy = "bookingLine")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<BookingLineExtraLuggage> extraLuggages;

    @OneToMany(mappedBy = "bookingLine")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<BookingLineSeat> seats;



    public BookingLine() {
    }

    public String getBookingLineId() {
        return bookingLineId;
    }

    public void setBookingLineId(String bookingLineId) {
        this.bookingLineId = bookingLineId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Person getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Person createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Person getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Person updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCreatedClientId() {
        return createdClientId;
    }

    public void setCreatedClientId(String createdClientId) {
        this.createdClientId = createdClientId;
    }

    public String getUpdateClientId() {
        return updateClientId;
    }

    public void setUpdateClientId(String updateClientId) {
        this.updateClientId = updateClientId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
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

    public Organization getTransferredFrom() {
        return transferredFrom;
    }

    public void setTransferredFrom(Organization transferredFrom) {
        this.transferredFrom = transferredFrom;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public Date getBoardingDate() {
        return boardingDate;
    }

    public void setBoardingDate(Date boardingDate) {
        this.boardingDate = boardingDate;
    }

    public BookingCancellation getCancellation() {
        return cancellation;
    }

    public void setCancellation(BookingCancellation cancellation) {
        this.cancellation = cancellation;
    }


    public Collection<BookingLineLuggage> getLuggages() {
        return luggages;
    }

    public void setLuggages(Collection<BookingLineLuggage> luggages) {
        this.luggages = luggages;
    }

    public Collection<BookingLineExtraLuggage> getExtraLuggages() {
        return extraLuggages;
    }

    public void setExtraLuggages(Collection<BookingLineExtraLuggage> extraLuggages) {
        this.extraLuggages = extraLuggages;
    }

    public Collection<BookingLineSeat> getSeats() {
        return seats;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setSeats(Collection<BookingLineSeat> seats) {
        this.seats = seats;
    }
}
