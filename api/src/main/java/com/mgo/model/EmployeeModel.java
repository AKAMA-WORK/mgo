package com.mgo.model;



import com.mgo.entity.Employee;
import com.mgo.model.person.PersonModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "Employee")
public class EmployeeModel {
    @Schema(name = "employeeId", description = "The employee id")
    String employeeId;

    @Schema(name = "identificationNumber", description = "The identification number")
    String identificationNumber;


    @Schema(name = "position", description = "The position of employee", ref = "Position")
    PositionModel position;


    @Schema(name = "person", description = "The employee", ref = "Person")
    PersonModel person;

    @Schema(name = "status", description = "The status",enumeration = {
            "ACTIVE", "ARCHIVED"
    })
    String status;

    public EmployeeModel() {
    }

    public EmployeeModel(Employee employee) {
        this.employeeId = employee.getEmployeeId();
        this.identificationNumber = employee.getIdentificationNumber();
        this.status = employee.getStatus();
        this.position = employee.getPosition()!=null ? new PositionModel(employee.getPosition()):null;
        this.person = new PersonModel(employee.getPerson());

    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public PositionModel getPosition() {
        return position;
    }

    public void setPosition(PositionModel position) {
        this.position = position;
    }

    public PersonModel getPerson() {
        return person;
    }

    public void setPerson(PersonModel person) {
        this.person = person;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}