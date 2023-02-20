package com.mgo.model.booking;


import com.mgo.entity.BookingLineExtraLuggage;
import com.mgo.model.common.PaymentMethod;
import com.mgo.util.DateTimeFormat;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(name = "BookingLineExtraLuggage")
public class BookingLineExtraLuggageModel {

    @Schema(name = "bookingLineExtraLuggageId", description = "The id")
     String bookingLineExtraLuggageId;

    @Schema(name = "weight", description = "The weight")
     Double weight;


    @Schema(name = "amount", description = "The amount")
    Double amount;

    @Schema(name = "paymentId", description = "The payment id")
    String paymentId;

    @Schema(name = "paymentMethod", description = "The payment method", enumeration = {
            PaymentMethod.ORANGE_MONEY,PaymentMethod.MVOLA,PaymentMethod.AIRTEL_MONEY,PaymentMethod.CREDIT_CARD,PaymentMethod.CASH
    })
    String paymentMethod;

    @Schema(name = "paymentDate", description = "The payment of booking")
    String paymentDate;

    public BookingLineExtraLuggageModel() {
    }

    public BookingLineExtraLuggageModel(BookingLineExtraLuggage extraLuggage) {
        this.setBookingLineExtraLuggageId(extraLuggage.getBookingLineExtraLuggageId());
        this.setWeight(extraLuggage.getWeight());
        this.setAmount(extraLuggage.getAmount());
        this.setPaymentId(extraLuggage.getPaymentId());
        this.setPaymentMethod(extraLuggage.getPaymentMethod());
        this.setPaymentDate(DateTimeFormat.formatDatetime(extraLuggage.getPaymentDate()));
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getBookingLineExtraLuggageId() {
        return bookingLineExtraLuggageId;
    }

    public void setBookingLineExtraLuggageId(String bookingLineExtraLuggageId) {
        this.bookingLineExtraLuggageId = bookingLineExtraLuggageId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
}