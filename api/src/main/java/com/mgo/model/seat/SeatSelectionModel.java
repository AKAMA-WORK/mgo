package com.mgo.model.seat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@AllArgsConstructor
@Getter(onMethod = {})
@Data
@Builder(toBuilder = true)
@Schema(name = "SeatSelection")
public class SeatSelectionModel {
    @Schema(description = "The id of departure.")
    String departureId;

    @Schema(description = "The seat number.")
    Integer seatNumber;

    @Schema(name = "personId", description = "The id of Person")
    String personId;// If user is authenticated

    @Schema(name = "clientType", description = "The client Type")
    String clientType;// WEB | BACK_OFFICE | USSD | FACEBOOK_BOT

    @Schema(name = "correlationId", description = "The correlation id.")
    String correlationId;// Session Id for example/ Phone number for USSD // etc ...


    @Schema(name = "socketId", description = "The socket id.")
    String socketId;

}
