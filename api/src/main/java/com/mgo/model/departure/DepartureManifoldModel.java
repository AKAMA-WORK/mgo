package com.mgo.model.departure;

import com.mgo.model.common.Civility;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(name = "DepartureManifold")
public class DepartureManifoldModel {
    @Schema(name = "departureManifoldId", description = "The id")
     String departureManifoldId;


    @Schema(description = "The seat number")
    Integer seatNumber;


    @Schema(name = "civility", description = "The civility of client", enumeration = {
            Civility.MONSIEUR, Civility.MADAME, Civility.MADEMOISELLE
    })
    String civility;

    @Schema(name = "firstName", description = "The first name")
    String firstName;


    @Schema(name = "lastName", description = "The last name")
    String lastName;

    @Schema(name = "address", description = "The address")
    String address;

    @Schema(name = "phoneNumber", description = "The phone of person", example = "+261320718212")
    String phoneNumber;

    @Schema(name = "idNumber", description = "The id number ")
    String idNumber;

    @Schema(name = "idIssueDate", description = "The id issue date")
    String idIssueDate;

    @Schema(name = "idIssueLocation", description = "The id issue location")
    String idIssueLocation;

    @Schema(name = "idDuplicateDate", description = "The id duplicate date")
    String idDuplicateDate;

    @Schema(name = "idDuplicateLocation", description = "The id duplicate location")
    String idDuplicateLocation;

    @Schema(name = "idType", description = "The id type", enumeration = {
            "PASSPORT", "CIN", "CARTE_ETUDIANT"
    })
    String idType;



    @Schema(name = "contactCivility", description = "The civility of contact", enumeration = {
            "Mr", "Mme", "Mlle"
    })
    String contactCivility;


    @Schema(name = "contactName", description = "The full name of contact")
    String contactName;

    @Schema(name = "contactFirstName", description = "The first name")
    String contactFirstName;


    @Schema(name = "contactLastName", description = "The last name")
    String contactLastName;


    @Schema(name = "contactAddress", description = "The address of contact")
    String contactAddress;


    @Schema(name = "contactPhoneNumber", description = "The phone number of contact")
    String contactPhoneNumber;

    @Schema(name = "contactPhoneNumber2", description = "The second phone number of contact")
    String contactPhoneNumber2;


    public String getDepartureManifoldId() {
        return departureManifoldId;
    }

    public void setDepartureManifoldId(String departureManifoldId) {
        this.departureManifoldId = departureManifoldId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdIssueDate() {
        return idIssueDate;
    }

    public void setIdIssueDate(String idIssueDate) {
        this.idIssueDate = idIssueDate;
    }

    public String getIdIssueLocation() {
        return idIssueLocation;
    }

    public void setIdIssueLocation(String idIssueLocation) {
        this.idIssueLocation = idIssueLocation;
    }

    public String getIdDuplicateDate() {
        return idDuplicateDate;
    }

    public void setIdDuplicateDate(String idDuplicateDate) {
        this.idDuplicateDate = idDuplicateDate;
    }

    public String getIdDuplicateLocation() {
        return idDuplicateLocation;
    }

    public void setIdDuplicateLocation(String idDuplicateLocation) {
        this.idDuplicateLocation = idDuplicateLocation;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getContactCivility() {
        return contactCivility;
    }

    public void setContactCivility(String contactCivility) {
        this.contactCivility = contactCivility;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
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
}