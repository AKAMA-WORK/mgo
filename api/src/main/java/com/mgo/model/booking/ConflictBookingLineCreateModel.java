package com.mgo.model.booking;

import com.mgo.entity.Departure;
import com.mgo.model.departure.DepartureModel;
import com.mgo.service.departure.DepartureService;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Date;


@Schema(name = "ConflictBookingLineCreate")
public class ConflictBookingLineCreateModel {
    @Schema(name = "lineCreate", description = "The sent parameters")
    BookingLineCreateModel lineCreate;

    @Schema(name = "departure", description = "The departure info", ref = "Departure" )
    DepartureModel departure;


    public ConflictBookingLineCreateModel() {
    }

    public ConflictBookingLineCreateModel(BookingLineCreateModel lineCreateModel, Date now) {
        this.lineCreate = lineCreateModel;
        Departure departureEntity = Departure.findById(lineCreateModel.getDepartureId());
        this.departure = new DepartureModel(departureEntity, DepartureService.findDepartureOrganizationConfig(departureEntity),now);
    }

    public BookingLineCreateModel getLineCreate() {
        return lineCreate;
    }

    public void setLineCreate(BookingLineCreateModel lineCreate) {
        this.lineCreate = lineCreate;
    }


    public DepartureModel getDeparture() {
        return departure;
    }

    public void setDeparture(DepartureModel departure) {
        this.departure = departure;
    }
}