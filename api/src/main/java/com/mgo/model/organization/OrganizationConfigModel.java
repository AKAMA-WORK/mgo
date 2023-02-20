package com.mgo.model.organization;

import com.mgo.entity.OrganizationConfig;
import com.mgo.model.CategoryModel;
import com.mgo.model.city.CityModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "OrganizationConfig")
public class OrganizationConfigModel {
    @Schema(name = "organizationConfigId", description = "The id of config")
     String organizationConfigId;

    @Schema(name = "authorizedLuggage", description = "The authorized luggage")
     Double authorizedLuggage;

    @Schema(name = "priceExtraLuggage", description = "The unit price of extra luggage")
     Double priceExtraLuggage;

    @Schema(name = "bookingCancellationPenaltyAmount", description = "The cancellation penalty amount")
    Double bookingCancellationPenaltyAmount;

    @Schema(name = "bookingCancellationPenaltyPercent", description = "The cancellation penalty percent")
    Double bookingCancellationPenaltyPercent;

    @Schema(name = "transferCommission", description = "The transfer commission")
    Double transferCommission;


    @Schema(description = "The start in", ref = "City")
    CityModel from;

    @Schema(description = "The destination", ref = "City")
    CityModel to;

    @Schema(description = "The category of departure.", ref = "Category")
    CategoryModel category;


    public OrganizationConfigModel() {
    }

    public OrganizationConfigModel(OrganizationConfig config) {
        this.organizationConfigId = config.getOrganizationConfigId();
        this.authorizedLuggage = config.getAuthorizedLuggage();
        this.priceExtraLuggage = config.getPriceExtraLuggage();
        this.bookingCancellationPenaltyAmount = config.getBookingCancellationPenaltyAmount();
        this.bookingCancellationPenaltyPercent = config.getBookingCancellationPenaltyPercent();
        this.transferCommission = config.getTransferCommission();
        this.from = config.getFrom()!=null ? new CityModel(config.getFrom()) : null;
        this.to = config.getTo()!=null ? new CityModel(config.getTo()) : null;
        this.category = config.getCategory()!=null ? new CategoryModel(config.getCategory()) : null;
    }

    public String getOrganizationConfigId() {
        return organizationConfigId;
    }

    public void setOrganizationConfigId(String organizationConfigId) {
        this.organizationConfigId = organizationConfigId;
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

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }
}