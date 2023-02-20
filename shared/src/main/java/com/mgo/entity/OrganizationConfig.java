
package com.mgo.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "organization_config")
public class OrganizationConfig extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_organization_config")

    private String organizationConfigId;

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

    @Column(name = "authorized_luggage",precision = 2)
    private Double authorizedLuggage;

    @Column(name = "price_extra_luggage",precision = 2)
    private Double priceExtraLuggage;

    @Column(name = "booking_cancellation_penalty_amount",precision = 2)
    private Double bookingCancellationPenaltyAmount;

    @Column(name = "booking_cancellation_penalty_percent",precision = 2)
    private Double bookingCancellationPenaltyPercent;

    @Column(name = "transfer_commission",precision = 2)
    private Double transferCommission;


    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    @ManyToOne
    private Category category;


    @JoinColumn(name = "to_id_city", referencedColumnName = "id_city")
    @ManyToOne
    private City to;

    @JoinColumn(name = "from_id_city", referencedColumnName = "id_city")
    @ManyToOne
    private City from;


    @JoinColumn(name = "id_organization", referencedColumnName = "id_organization")
    @ManyToOne
    private Organization organization;

    public OrganizationConfig() {
    }

    public String getOrganizationConfigId() {
        return organizationConfigId;
    }

    public void setOrganizationConfigId(String organizationConfigId) {
        this.organizationConfigId = organizationConfigId;
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

    public Double getAuthorizedLuggage() {
        return authorizedLuggage;
    }

    public void setAuthorizedLuggage(Double authorizedLuggage) {
        this.authorizedLuggage = authorizedLuggage;
    }

    public Double getPriceExtraLuggage() {
        return priceExtraLuggage;
    }

    public void setPriceExtraLuggage(Double priceExtraLuggage) {
        this.priceExtraLuggage = priceExtraLuggage;
    }

    public Double getBookingCancellationPenaltyAmount() {
        return bookingCancellationPenaltyAmount;
    }

    public void setBookingCancellationPenaltyAmount(Double bookingCancellationPenaltyAmount) {
        this.bookingCancellationPenaltyAmount = bookingCancellationPenaltyAmount;
    }

    public Double getBookingCancellationPenaltyPercent() {
        return bookingCancellationPenaltyPercent;
    }

    public void setBookingCancellationPenaltyPercent(Double bookingCancellationPenaltyPercent) {
        this.bookingCancellationPenaltyPercent = bookingCancellationPenaltyPercent;
    }

    public Double getTransferCommission() {
        return transferCommission;
    }

    public void setTransferCommission(Double transferCommission) {
        this.transferCommission = transferCommission;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public City getTo() {
        return to;
    }

    public void setTo(City to) {
        this.to = to;
    }

    public City getFrom() {
        return from;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
