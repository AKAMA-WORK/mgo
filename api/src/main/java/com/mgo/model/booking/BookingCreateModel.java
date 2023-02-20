package com.mgo.model.booking;

import java.util.List;

import com.mgo.model.common.PaymentMethod;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter(onMethod = {})
@Data
@Builder(toBuilder = true)
@Schema(name = "BookingCreate")
public class BookingCreateModel {

    @Schema(name = "paymentId", description = "The payment id")
    String paymentId;

    @Schema(name = "paymentMethod", description = "The payment method",
    enumeration = {
            PaymentMethod.ORANGE_MONEY,PaymentMethod.MVOLA,PaymentMethod.AIRTEL_MONEY,PaymentMethod.CREDIT_CARD,PaymentMethod.CASH
    })
    String paymentMethod;


    @Schema(name = "paymentDate", description = "The payment date", example = "2022-11-04 14:00:00")
    String paymentDate;

    @Schema(name = "clientIdPerson", description = "The client")
    String clientIdPerson;

    @Schema(name = "lines", description = "The lines", ref = "BookingLineCreate", type = SchemaType.ARRAY)
    List<BookingLineCreateModel> lines;

    @Schema(name = "status", description = "The status of booking", enumeration = {
            BookingStatus.INITIATED,BookingStatus.CONFIRMED, BookingStatus.CANCELLED
    })
    String status;

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
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

    public List<BookingLineCreateModel> getLines() {
        return lines;
    }

    public void setLines(List<BookingLineCreateModel> lines) {
        this.lines = lines;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}