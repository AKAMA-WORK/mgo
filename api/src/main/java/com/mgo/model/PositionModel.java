package com.mgo.model;

import com.mgo.entity.Position;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(name = "Position")
public class PositionModel {

    @Schema(name = "positionId", description = "The id of position")
     String positionId;

    @Schema(name = "code", description = "The code")
     String code;

    @Schema(name = "name", description = "The name of position")
     String name;


    public PositionModel() {
    }

    public PositionModel(Position position) {
        this.positionId = position.getPositionId();
        this.code = position.getCode();
        this.name = position.getName();
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}