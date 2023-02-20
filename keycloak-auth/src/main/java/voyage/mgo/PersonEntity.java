package voyage.mgo;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQuery(name = "findPerson", query = "" +
        "select p " +
        "  from Person p " +
        " where p.userId = :userId ")
@NamedQuery(name = "deletePerson", query = "" +
        "delete " +
        "  from Person p " +
        " where p.userId = :userId")

@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_person")
    private Integer personId;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "creation_id_person")
    private Integer creationIdPerson;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(name = "update_id_person")
    private Integer updateIdPerson;

    @Column(name = "creation_client_id")
    private String createdClientId;

    @Column(name = "update_client_id")
    private String updateClientId;

    @Column(name = "civility")
    private String civility;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "phone_number")
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

    @Column(name = "id_type")
    private String idType;

    @Column(name = "id_user")
    private String userId;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getCreationIdPerson() {
        return creationIdPerson;
    }

    public void setCreationIdPerson(Integer creationIdPerson) {
        this.creationIdPerson = creationIdPerson;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getUpdateIdPerson() {
        return updateIdPerson;
    }

    public void setUpdateIdPerson(Integer updateIdPerson) {
        this.updateIdPerson = updateIdPerson;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
