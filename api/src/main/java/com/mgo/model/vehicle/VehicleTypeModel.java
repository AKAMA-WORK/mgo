package com.mgo.model.vehicle;


import com.mgo.entity.Vehicle;
import com.mgo.entity.VehicleType;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "VehicleType")
public class VehicleTypeModel {
    @Schema(name = "vehicleTypeId", description = "The id of vehicle type")
     String vehicleTypeId;

    @Schema(name = "name", description = "The name")
     String name;


    public VehicleTypeModel() {
    }

    public VehicleTypeModel(VehicleType type) {
        this.vehicleTypeId = type.getVehicleTypeId();
        this.name = type.getName();
    }

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}