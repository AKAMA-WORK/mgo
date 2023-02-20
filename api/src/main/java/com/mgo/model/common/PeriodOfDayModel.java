package com.mgo.model.common;

import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

//@NoArgsConstructor
//@AllArgsConstructor
@Getter(onMethod = {})
@Data
@Builder(toBuilder = true)
@Schema(name = "PeriodOfDay")
public class PeriodOfDayModel {

    @Schema(name = "id", description = "The id")
    String id;

    @Schema(name = "label", description = "The label")
    String label;

    @Schema(name = "startTime", description = "The start hour")
    String startTime;

    @Schema(name = "endTime", description = "The end hour")
    String endTime;

    public PeriodOfDayModel() {
    }

    public PeriodOfDayModel(String id, String label, String startHour, String endHour) {
        this.id = id;
        this.label = label;
        this.startTime = startHour;
        this.endTime = endHour;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
