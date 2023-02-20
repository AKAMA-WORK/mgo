package com.mgo.model.departure;

import com.mgo.entity.Departure;
import com.mgo.entity.OrganizationConfig;
import com.mgo.model.*;
import com.mgo.model.city.CityModel;
import com.mgo.model.organization.OrganizationConfigModel;
import com.mgo.model.organization.OrganizationModel;
import com.mgo.model.person.PersonModel;
import com.mgo.model.vehicle.VehicleModel;
import com.mgo.model.vehicle.VehicleTypeModel;
import com.mgo.service.departure.DepartureService;
import com.mgo.util.DateTimeFormat;
import com.mgo.util.Departures;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@AllArgsConstructor
@Getter(onMethod = {})
@Data
@Builder(toBuilder = true)
@Schema(name = "Departure")
public class DepartureModel {

    @Schema(description = "The id of departure.")
     String departureId;

    @Schema(description = "The date of departure.", example = "2022-04-01")
     String date;
    @Schema(description = "The time of departure.", example = "08:00")
    String time;


    @Schema(description = "The end date of departure.", example = "2022-04-01")
    String endDate;

    @Schema(description = "The end time of departure.", example = "08:00")
    String endTime;


    @Schema(description = "The  estimated arrival time.", example = "08:00")
    String estimatedArrivalTime;



    @Schema(description = "The car registration")
    String carRegistrationNumber;

    @Schema(description = "The price")
     Double price;

    @Schema(description = "The vehicle", ref = "Vehicle")
    VehicleModel vehicle;


    @Schema(description = "The driver", ref = "Employee")
    EmployeeModel driver;

    @Schema(description = "The second driver", ref = "Employee")
     EmployeeModel driver2;

    @Schema(description = "The category of departure.", ref = "Category")
    CategoryModel category;


    @Schema(description = "The start in", ref = "City")
    CityModel from;

    @Schema(description = "The destination", ref = "City")
     CityModel to;


    @Schema(description = "The organization", ref = "Organization")
    OrganizationModel organization;

    @Schema(description = "The status of departure", example = "ONDATE", enumeration = { "ONDATE", "CANCELLED",
            "TRANSFERED"})
     String status;


    @Schema(name = "lockUntil", description = "Lock seat until")
    String lockUntil;


    @Schema(name = "lockedBy", description = "Lock seat by", ref = "Person")
    PersonModel lockedBy;

    @Schema(name = "lockedByPhone", description = "Lock seat by phone number")
    String lockedByPhone;


    @Schema(name = "title", description = "The departure title")
     String title;


    @Schema(description = "The lines count")
    Integer lines;

    @Schema(description = "The columns count")
    Integer columns;


    @Schema(description = "The number of available seats", example = "18")
    Integer availableSeats;

   @Schema(description = "The number of seats", example = "18")
    Integer totalSeats;

    @Schema(description = "The vehicle type", ref = "VehicleType")
    VehicleTypeModel vehicleType;

    @Schema(description = "The departure config", ref = "OrganizationConfig")
    OrganizationConfigModel config;


    @Schema(name = "seats", description = "The seats", ref = "DepartureVehicleSeat",type = SchemaType.ARRAY)
    List<DepartureVehicleSeatModel> seats;

    public DepartureModel() {
    }

    public DepartureModel(Departure departure, OrganizationConfig config, Date now){
        this.departureId = departure.getDepartureId();
        this.date = DateTimeFormat.formatDate(departure.getDateTime());
        this.time = DateTimeFormat.formatHour(departure.getDateTime());
        this.endDate =  DateTimeFormat.formatDate(departure.getEndDateTime());
        this.endTime = DateTimeFormat.formatHour(departure.getEndDateTime());
        this.carRegistrationNumber = departure.getCarRegistrationNumber();
        this.price= departure.getPrice();
        this.driver = departure.getDriver()!=null ? new EmployeeModel(departure.getDriver()) : null;
        this.driver2 = departure.getDriver()!=null ? new EmployeeModel(departure.getDriver2()) : null;
        this.category = departure.getCategory()!=null ? new CategoryModel(departure.getCategory()) : null;
        this.from = departure.getFrom()!=null ? new CityModel(departure.getFrom()):null;
        this.to = departure.getTo()!=null ? new CityModel(departure.getTo()) : null;
        this.organization = departure.getOrganization()!=null ? new OrganizationModel(departure.getOrganization()):null;
        this.status = departure.getStatus();
        this.lockUntil = DateTimeFormat.formatDatetime(departure.getLockUntil());
        this.lockedBy = departure.getLockedByPerson()!=null ? new PersonModel(departure.getLockedByPerson()) : null;
        this.lockedByPhone = departure.getLockedByPhone();
        this.lines = departure.getLines();
        this.columns = departure.getColumns();
        this.title = Departures.getDepartureInfo(departure);
        this.seats = departure.getSeats().stream().map(seat -> new DepartureVehicleSeatModel(seat,now)).collect(Collectors.toList());
        this.vehicle = departure.getVehicle()!=null? new VehicleModel(departure.getVehicle()):null;
        this.vehicleType = departure.getVehicleType()!=null ? new VehicleTypeModel(departure.getVehicleType()) : null;
        this.availableSeats = (int) this.seats.stream().filter(s -> s.getSeatNumber()>0 && s.status.equals(DepartureVehicleSeatStatus.AVAILABLE)).count();
        this.config = config!=null ? new OrganizationConfigModel(config):null;
        this.estimatedArrivalTime = DepartureService.getEstimatedArrivalTime(departure);
    }

    public String getDepartureId() {
        return departureId;
    }

    public void setDepartureId(String departureId) {
        this.departureId = departureId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public OrganizationConfigModel getConfig() {
        return config;
    }

    public void setConfig(OrganizationConfigModel config) {
        this.config = config;
    }

    public String getCarRegistrationNumber() {
        return carRegistrationNumber;
    }

    public void setCarRegistrationNumber(String carRegistrationNumber) {
        this.carRegistrationNumber = carRegistrationNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public VehicleModel getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleModel vehicle) {
        this.vehicle = vehicle;
    }

    public EmployeeModel getDriver() {
        return driver;
    }

    public void setDriver(EmployeeModel driver) {
        this.driver = driver;
    }

    public EmployeeModel getDriver2() {
        return driver2;
    }

    public void setDriver2(EmployeeModel driver2) {
        this.driver2 = driver2;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public CityModel getFrom() {
        return from;
    }

    public void setFrom(CityModel from) {
        this.from = from;
    }

    public CityModel getTo() {
        return to;
    }

    public void setTo(CityModel to) {
        this.to = to;
    }

    public OrganizationModel getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationModel organization) {
        this.organization = organization;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLockUntil() {
        return lockUntil;
    }

    public void setLockUntil(String lockUntil) {
        this.lockUntil = lockUntil;
    }

    public PersonModel getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(PersonModel lockedBy) {
        this.lockedBy = lockedBy;
    }

    public String getLockedByPhone() {
        return lockedByPhone;
    }

    public void setLockedByPhone(String lockedByPhone) {
        this.lockedByPhone = lockedByPhone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLines() {
        return lines;
    }

    public void setLines(Integer lines) {
        this.lines = lines;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public List<DepartureVehicleSeatModel> getSeats() {
        return seats;
    }

    public void setSeats(List<DepartureVehicleSeatModel> seats) {
        this.seats = seats;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public VehicleTypeModel getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleTypeModel vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public void setEstimatedArrivalTime(String estimatedArrivalTime) {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }
}
