package com.mgo.model.booking;

import lombok.*;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter(onMethod = {})
@Data
@Builder(toBuilder = true)
@Schema(name = "ConflictBookingResponse")
public class ConflictBookingResponseModel {

    @Schema(name = "paymentId", description = "The payment id")
    String paymentId;

    @Schema(name = "paymentMethod", description = "The payment method")
    String paymentMethod;

    @Schema(name = "clientIdPerson", description = "The client")
    String clientIdPerson;

    @Schema(name = "lines", description = "The lines", ref = "BookingLineCreate", type = SchemaType.ARRAY)
    List<ConflictBookingLineCreateModel> lines;

    @Schema(name = "status", description = "The status of booking", enumeration = {
            BookingStatus.INITIATED,BookingStatus.CONFIRMED,BookingStatus.CANCELLED
    })
    String status;


    public ConflictBookingResponseModel() {
    }

    public ConflictBookingResponseModel(BookingCreateModel createModel, Date now) {

        this.paymentId = createModel.getPaymentId();
        this.paymentMethod = createModel.getPaymentMethod();
        this.clientIdPerson = createModel.getClientIdPerson();
        this.status = createModel.getStatus();
        this.lines = createModel.getLines().stream().map(lineCreateModel -> new ConflictBookingLineCreateModel(lineCreateModel,now) ).collect(Collectors.toList());

    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getClientIdPerson() {
        return clientIdPerson;
    }

    public void setClientIdPerson(String clientIdPerson) {
        this.clientIdPerson = clientIdPerson;
    }

    public List<ConflictBookingLineCreateModel> getLines() {
        return lines;
    }

    public void setLines(List<ConflictBookingLineCreateModel> lines) {
        this.lines = lines;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}