package com.mgo.model.booking;

import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@NoArgsConstructor
@AllArgsConstructor
@Getter(onMethod = {})
@Data
@Builder(toBuilder = true)
@Schema(name = "BookingUpdate")
public class BookingUpdateModel {

    @Schema(name = "paymentId", description = "The payment id")
    String paymentId;

    @Schema(name = "paymentMethod", description = "The payment method")
    String paymentMethod;

    @Schema(name = "paymentDate", description = "The payment date", example = "2022-11-04 14:00:00")
    String paymentDate;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}