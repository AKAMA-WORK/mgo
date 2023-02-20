package com.mgo.model.booking;


import com.mgo.entity.BookingCancellation;
import com.mgo.model.common.PaymentMethod;
import com.mgo.util.DateTimeFormat;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(name = "BookingCancellation")
public class BookingCancellationModel {

    @Schema(name = "bookingCancellationId", description = "The id")
     String bookingCancellationId;

    @Schema(name = "date", description = "The date")
     String date;


    @Schema(name = "amountReimbursed", description = "The amount")
     Double amountReimbursed;

    @Schema(name = "paymentId", description = "The payment id")
    String paymentId;

    @Schema(name = "paymentMethod", description = "The payment method", enumeration = {
            PaymentMethod.ORANGE_MONEY,PaymentMethod.MVOLA,PaymentMethod.AIRTEL_MONEY,PaymentMethod.CREDIT_CARD,PaymentMethod.CASH
    })
    String paymentMethod;

    @Schema(name = "paymentDate", description = "The payment of booking", example = "2022-11-04 14:00:00")
    String paymentDate;


    @Schema(name = "status", description = "The status",enumeration = {
            BookingCancellationStatus.PENDING, BookingCancellationStatus.REFUNDED
    })
    String status;

    public BookingCancellationModel() {
    }

    public BookingCancellationModel(BookingCancellation cancellation) {
        this.setBookingCancellationId(cancellation.getBookingCancellationId());
        this.setDate(DateTimeFormat.formatDatetime(cancellation.getDate()));
        this.setPaymentId(cancellation.getPaymentId());
        this.setPaymentMethod(cancellation.getPaymentMethod());
        this.setStatus(cancellation.getStatus());
        this.setAmountReimbursed(cancellation.getAmountReimbursed());
        this.setPaymentDate(DateTimeFormat.formatDatetime(cancellation.getPaymentDate()));
    }


    public String getBookingCancellationId() {
        return bookingCancellationId;
    }

    public void setBookingCancellationId(String bookingCancellationId) {
        this.bookingCancellationId = bookingCancellationId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmountReimbursed() {
        return amountReimbursed;
    }

    public void setAmountReimbursed(Double amountReimbursed) {
        this.amountReimbursed = amountReimbursed;
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