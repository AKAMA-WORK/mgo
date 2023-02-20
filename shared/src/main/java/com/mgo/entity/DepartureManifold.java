package com.mgo.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "departure_manifold")
public class DepartureManifold extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_departure_manifold")
    private String departureManifoldId;

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


    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    @ManyToOne
    private Person person;

    @Column(name = "phone_number",length = 50)
    private String phoneNumber;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Column(name = "civility",length = 10)
    private String civility;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "id_issue_date")
    @Temporal(TemporalType.DATE)
    private Date idIssueDate;

    @Column(name = "id_issue_location")
    private String idIssueLocation;

    @Column(name = "id_duplicate_date")
    @Temporal(TemporalType.DATE)
    private Date idDuplicateDate;

    @Column(name = "id_duplicate_location")
    private String idDuplicateLocation;

    @Column(name = "contact_civility")
    private String contactCivility;

    @Column(name = "contact_first_name")
    private String contactFirstName;

    @Column(name = "contact_last_name")
    private String contactLastName;

    @Column(name = "contact_address")
    private String contactAddress;

    @Column(name = "contact_phone_number",length = 50)
    private String contactPhoneNumber;

    @Column(name = "contact_phone_number2",length = 50)
    private String contactPhoneNumber2;

    @Column(name = "date")
    private String date;


    @JoinColumn(name = "id_person_contact", referencedColumnName = "id_person")
    @ManyToOne
    private Person contact;

    @JoinColumn(name = "id_departure", referencedColumnName = "id_departure")
    @ManyToOne
    private Departure departure;

    @Column(name = "id_type",length = 50)
    private String idType;


    public DepartureManifold() {
    }


    public String getDepartureManifoldId() {
        return departureManifoldId;
    }

    public void setDepartureManifoldId(String departureManifoldId) {
        this.departureManifoldId = departureManifoldId;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCivility() {
        return civility;
    }

    public void setCivility(String civility) {
        this.civility = civility;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getIdIssueDate() {
        return idIssueDate;
    }

    public void setIdIssueDate(Date idIssueDate) {
        this.idIssueDate = idIssueDate;
    }

    public String getIdIssueLocation() {
        return idIssueLocation;
    }

    public void setIdIssueLocation(String idIssueLocation) {
        this.idIssueLocation = idIssueLocation;
    }

    public Date getIdDuplicateDate() {
        return idDuplicateDate;
    }

    public void setIdDuplicateDate(Date idDuplicateDate) {
        this.idDuplicateDate = idDuplicateDate;
    }

    public String getIdDuplicateLocation() {
        return idDuplicateLocation;
    }

    public void setIdDuplicateLocation(String idDuplicateLocation) {
        this.idDuplicateLocation = idDuplicateLocation;
    }

    public String getContactCivility() {
        return contactCivility;
    }

    public void setContactCivility(String contactCivility) {
        this.contactCivility = contactCivility;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactPhoneNumber2() {
        return contactPhoneNumber2;
    }

    public void setContactPhoneNumber2(String contactPhoneNumber2) {
        this.contactPhoneNumber2 = contactPhoneNumber2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Person getContact() {
        return contact;
    }

    public void setContact(Person contact) {
        this.contact = contact;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }
}
