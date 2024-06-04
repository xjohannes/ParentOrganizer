package com.axeweb.parentorganizr.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

@Data
public class Location {

    @Id
    private Integer id;
    private final String locationName;
    private final String place;
    private final String building;
    private final String roomNr;
    private final int floor;
    private final String description;
    @Version
    private Integer version;

    public Location(String locationName, String place, String building, String roomNr, int floor, String description) {
        this.locationName = locationName;
        this.place = place;
        this.building = building;
        this.roomNr = roomNr;
        this.floor = floor;
        this.description = description;
    }

}
