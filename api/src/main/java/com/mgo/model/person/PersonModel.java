package com.mgo.model.person;

import com.mgo.entity.Person;
import com.mgo.model.common.Civility;
import com.mgo.util.DateTimeFormat;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(name = "Person")
public class PersonModel {
    @Schema(name = "personId", description = "The id of Person")
    String personId;

    @Schema(name = "civility", description = "The civility of client", enumeration = {
            Civility.MONSIEUR, Civility.MADAME, Civility.MADEMOISELLE
    })
    String civility;

    @Schema(name = "firstName", description = "The first name")
    String firstName;


    @Schema(name = "lastName", description = "The last name")
    String lastName;

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

    @Schema(name = "userId", description = "The user id")
    String userId;

    public PersonModel() {
    }

    public PersonModel(Person person) {
        this.personId = person.getPersonId();
        this.civility = person.getCivility();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phoneNumber = person.getPhoneNumber();
        this.idNumber = person.getIdNumber();
        this.idIssueDate = DateTimeFormat.formatDate(person.getIdIssueDate());
        this.idIssueLocation = person.getIdIssueLocation();
        this.idDuplicateDate = DateTimeFormat.formatDate(person.getIdDuplicateDate());
        this.idDuplicateLocation = person.getIdDuplicateLocation();
        this.idType = person.getIdType();
        this.userId = person.getUserId();
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}