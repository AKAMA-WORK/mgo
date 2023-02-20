package com.mgo.model.booking;



import com.mgo.entity.Booking;
import com.mgo.model.common.PaymentMethod;
import com.mgo.model.person.PersonModel;
import com.mgo.util.DateTimeFormat;
import com.mgo.util.DateTimeUtils;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Schema(name = "Booking")
public class BookingModel {

    @Schema(name = "bookingId", description = "The id of booking")
     String bookingId;

    @Schema(name = "date", description = "The date of booking")
     String date;

    @Schema(name = "amount", description = "The amount")
    Double amount;

    @Schema(name = "paymentMethod", description = "The payment method", enumeration = {
            PaymentMethod.ORANGE_MONEY,PaymentMethod.MVOLA,PaymentMethod.AIRTEL_MONEY,PaymentMethod.CREDIT_CARD,PaymentMethod.CASH
    })
    String paymentMethod;

    @Schema(name = "paymentId", description = "The payment reference")
    String paymentId;

    @Schema(name = "paymentDate", description = "The payment of booking", example = "2022-11-04 14:00:00")
    String paymentDate;

    @Schema(name = "description", description = "The description")
     String description;

    @Schema(name = "status", description = "The status",enumeration = {
            BookingStatus.INITIATED,BookingStatus.CONFIRMED, BookingStatus.CANCELLED
    })
     String status;

    @Schema(name = "client", description = "The client", ref = "Person")
    PersonModel client;

    @Schema(name = "lines", description = "The lines", ref = "BookingLine",type = SchemaType.ARRAY)
    List<BookingLineModel> lines;

    @Schema(name = "cancellation", description = "The cancellation", ref = "BookingCancellation")
    BookingCancellationModel cancellation;

    @Schema(name = "waitConfirmUntil", description = "Wait confirm until")
    String waitConfirmUntil;


    @Schema(name = "waitConfirmIn", description = "Wait confirm in (seconds)")
    Long waitConfirmIn;

    public BookingModel() {
    }

    public BookingModel(Booking booking, Date now) {
        this.setBookingId(booking.getBookingId());
        this.setDate(DateTimeFormat.formatDatetime(booking.getDate()));
        this.setWaitConfirmUntil(DateTimeFormat.formatDatetime(booking.getWaitConfirmUntil()));
        this.setAmount(booking.getAmount());
        this.setPaymentMethod(booking.getPaymentMethod());
        this.setPaymentId(booking.getPaymentId());
        this.setDescription(booking.getDescription());
        this.setStatus(booking.getStatus());
        this.setClient(new PersonModel(booking.getClient()));
        this.setCancellation(booking.getCancellation()!=null? new BookingCancellationModel(booking.getCancellation()) : null);
        this.setLines(booking.getLines().stream().map(bookingLine -> new BookingLineModel(bookingLine,now)).collect(Collectors.toList()) );
        this.setPaymentDate(DateTimeFormat.formatDatetime(booking.getPaymentDate()));
        this.computeWaitConfirm(booking);
    }

    private void computeWaitConfirm(Booking booking){
        if(this.status.equals(BookingStatus.INITIATED)){
            this.setWaitConfirmIn(DateTimeUtils.secondsBetween(new Date(),booking.getWaitConfirmUntil()));
        }
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public PersonModel getClient() {
        return client;
    }

    public void setClient(PersonModel client) {
        this.client = client;
    }

    public List<BookingLineModel> getLines() {
        return lines;
    }

    public void setLines(List<BookingLineModel> lines) {
        this.lines = lines;
    }

    public BookingCancellationModel getCancellation() {
        return cancellation;
    }

    public void setCancellation(BookingCancellationModel cancellation) {
        this.cancellation = cancellation;
    }

    public String getWaitConfirmUntil() {
        return waitConfirmUntil;
    }

    public void setWaitConfirmUntil(String waitConfirmUntil) {
        this.waitConfirmUntil = waitConfirmUntil;
    }

    public Long getWaitConfirmIn() {
        return waitConfirmIn;
    }

    public void setWaitConfirmIn(Long waitConfirmIn) {
        this.waitConfirmIn = waitConfirmIn;
    }
}