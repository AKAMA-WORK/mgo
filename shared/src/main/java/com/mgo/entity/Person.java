
package com.mgo.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "person")
public class Person extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_person")
    private String personId;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date creationDate;

    @Column(name = "creation_id_person")
    private String creationIdPerson;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedDate;

    @Column(name = "update_id_person")
    private String updateIdPerson;

    @Column(name = "creation_client_id")
    private String createdClientId;

    @Column(name = "update_client_id")
    private String updateClientId;

    @Column(name = "civility",length = 10)
    private String civility;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "phone_number",length = 50)
    private String phoneNumber;

    @Column(name = "email")
    private String email;

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

    @Column(name = "id_type",length = 50)
    private String idType;

    @Column(name = "id_user")
    private String userId;


    public Person() {
    }



    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getCreationIdPerson() {
        return creationIdPerson;
    }

    public void setCreationIdPerson(String creationIdPerson) {
        this.creationIdPerson = creationIdPerson;
    }

    public String getUpdateIdPerson() {
        return updateIdPerson;
    }

    public void setUpdateIdPerson(String updateIdPerson) {
        this.updateIdPerson = updateIdPerson;
    }
}
