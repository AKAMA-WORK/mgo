package com.mgo;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.mgo.model.*;
import com.mgo.model.booking.*;
import com.mgo.model.city.CityModel;
import com.mgo.model.city.CitySearchResultModel;
import com.mgo.model.departure.*;
import com.mgo.model.organization.OrganizationConfigModel;
import com.mgo.model.organization.OrganizationModel;
import com.mgo.model.person.PersonModel;
import com.mgo.model.vehicle.*;
import com.mgo.resource.ErrorPayload;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@ApplicationPath("/")
@LoginConfig(authMethod = "MP-JWT")
@OpenAPIDefinition(
    info = @Info(
        title = "M-GO API",
        version = "1.0",
        contact = @Contact(
            name = "ANTSANIRINA Ginot",
            email = "ginot.antsanirina@gmail.com"     
        )
    ),

security = @SecurityRequirement(name = "oauth2", scopes = {}),
    components = @Components(
        securitySchemes = {
       

              @SecurityScheme(
                  securitySchemeName = "oauth2",
                  type = SecuritySchemeType.OAUTH2,
                  in = SecuritySchemeIn.HEADER,
                  flows = @OAuthFlows(
                      clientCredentials   = @OAuthFlow(
                        //  tokenUrl = "/oauth/token",
                         tokenUrl = "http://localhost:8082/realms/mgo/protocol/openid-connect/token",
                          scopes = {}
                      )
                  )
              )
        },
        requestBodies = {
            @RequestBody(
                name = "BookingCreate",
                description = "The Booking to create",
                required = true,
                content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "BookingCreate"))
            ),
                @RequestBody(
                        name = "BookingUpdate",
                        description = "The updated booking information",
                        required = true,
                        content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "BookingUpdate"))
                ),

                @RequestBody(
                        name = "DepartureCreate",
                        description = "The Departure to create",
                        required = true,
                        content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "DepartureCreate"))
                ),


                @RequestBody(
                        name = "DepartureBulkCreate",
                        description = "The Departures to create",
                        required = true,
                        content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "DepartureBulkCreate"))
                ),
        },
        responses = {
            @APIResponse(
                name = "Unauthorized",
                responseCode = "401",
                description = "Unauthorized",
                content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "ErrorPayload"))
            ),
            @APIResponse(
                name = "Forbidden",
                responseCode = "403",
                description = "Forbidden",
                content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "ErrorPayload"))
            ),
            @APIResponse(
                name = "NotFound",
                responseCode = "404",
                description = "Object Not found",
                content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "ErrorPayload"))
            ),
            @APIResponse(
                name = "InternalError",
                responseCode = "500",
                description = "Internal Server Error",
                content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "ErrorPayload"))
            ),
            @APIResponse(
                name = "ConflictBooking",
                responseCode = "409",
                description = "Conflict booking error",
                content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "ConflictBookingResponse"))
            ),
        },
        schemas = {
            @Schema(name = "ErrorPayload", implementation = ErrorPayload.class),
            @Schema(name = "DepartureCategory", implementation = DepartureCategoryModel.class),
            @Schema(name = " BookingCancellation", implementation = BookingCancellationModel.class),
            @Schema(name = "BookingLineExtraLuggage", implementation = BookingLineExtraLuggageModel.class),
                @Schema(name = "BookingLineLuggage", implementation = BookingLineLuggageModel.class),
                @Schema(name = "BookingLine", implementation = BookingLineModel.class),
                @Schema(name = "BookingLineSeat", implementation = BookingLineSeatModel.class),
                @Schema(name = "Booking", implementation = BookingModel.class),
                @Schema(name = "Category", implementation = CategoryModel.class),
                @Schema(name = "City", implementation = CityModel.class),
                @Schema(name = "ConflictBookingResponse", implementation = ConflictBookingResponseModel.class),
                @Schema(name = "DepartureCategory", implementation = DepartureCategoryModel.class),
                @Schema(name = "DepartureManifold", implementation = DepartureManifoldModel.class),
            @Schema(name = "Departure", implementation = DepartureModel.class),
                @Schema(name = "DepartureVehicle", implementation = DepartureVehicleModel.class),
            @Schema(name = "DepartureVehicleSeat", implementation = DepartureVehicleSeatModel.class),
                @Schema(name = "Employee", implementation = EmployeeModel.class),
                @Schema(name = "OrganizationConfig", implementation = OrganizationConfigModel.class),
                @Schema(name = "Organization", implementation = OrganizationModel.class),
                @Schema(name = "Person", implementation = PersonModel.class),
                @Schema(name = "Position", implementation = PositionModel.class),
            @Schema(name = "BookingCreate", implementation = BookingCreateModel.class),
                @Schema(name = "Vehicle", implementation = VehicleModel.class),
                @Schema(name = "VehicleSeat", implementation = VehicleSeatModel.class),
                @Schema(name = "VehicleModel", implementation = VehicleModelModel.class),
                @Schema(name = "VehicleModelSeat", implementation = VehicleModelSeatModel.class),
                @Schema(name = "VehicleType", implementation = VehicleTypeModel.class),
                @Schema(name = "CitySearchResult", implementation = CitySearchResultModel.class),

        }
    )
)


public class MGOApplication extends Application {
    
}
