package com.mgo.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author faraniarijaona
 */
@Entity
@Table(name = "booking")
public class Booking extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_booking")
    private String bookingId;

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


    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "amount",precision = 2)
    private Double amount;

    @Column(name = "payment_id",length = 100)
    private String paymentId;


    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @Lob
    @Column(name = "description",columnDefinition = "text")
    private String description;

    @JoinColumn(name = "client_id_person", referencedColumnName = "id_person")
    @ManyToOne
    private Person client;

    @Column(name = "payment_method",length = 100)
    private String paymentMethod;


    @Column(name = "wait_confirm_until")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waitConfirmUntil;

    @Column(name = "status",length = 50)
    private String status;// LOCKED | CONFIRMED

    @Column(name = "status_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusDate;

    @JoinColumn(name = "id_booking_cancellation", referencedColumnName = "id_booking_cancellation")
    @ManyToOne
    private BookingCancellation cancellation;

    @OneToMany(mappedBy = "booking")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<BookingLine> lines;



    public Booking() {
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BookingCancellation getCancellation() {
        return cancellation;
    }

    public void setCancellation(BookingCancellation cancellation) {
        this.cancellation = cancellation;
    }

    public Collection<BookingLine> getLines() {
        return lines;
    }

    public void setLines(Collection<BookingLine> lines) {
        this.lines = lines;
    }

    public Date getWaitConfirmUntil() {
        return waitConfirmUntil;
    }

    public void setWaitConfirmUntil(Date waitConfirmUntil) {
        this.waitConfirmUntil = waitConfirmUntil;
    }


    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
