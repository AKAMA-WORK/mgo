package com.mgo.model.organization;

import com.mgo.entity.Organization;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(name = "Organization")
public class OrganizationModel {
    @Schema(name = "organizationId", description = "The id of organization")
    String organizationId;

    @Schema(name = "name", description = "The name of organization")
    String name;

    @Schema(name = "address", description = "The address of organization")
    String address;

    @Schema(name = "logo", description = "The logo of organization")
    String logo;

    @Schema(name = "ticketLogo", description = "The ticket logo of organization")
    String ticketLogo;

    @Schema(name = "type", description = "The type of organization", enumeration = {
            OrganizationType.COMPANY, OrganizationType.COMPANY_AGENCY, OrganizationType.AGENCY
    })

    String type;


    @Schema(name = "idParent", description = "The type of parent organization")
    Integer idParent;


    public OrganizationModel(Organization entity){
         this.organizationId= entity.getOrganizationId();
         this.name= entity.getName();
         this.address = entity.getAddress();
         this.logo = entity.getLogo();
         this.ticketLogo = entity.getTicketLogo();
         this.type = entity.getType();
    }


    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTicketLogo() {
        return ticketLogo;
    }

    public void setTicketLogo(String ticketLogo) {
        this.ticketLogo = ticketLogo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIdParent() {
        return idParent;
    }

    public void setIdParent(Integer idParent) {
        this.idParent = idParent;
    }
}