package com.axeweb.parentorganizr.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.sql.Timestamp;

@Data
public class Location {

    @Id
    private Integer id;
    @NotEmpty
    private String locationName;
    @NotEmpty
    private String place;
    @NotEmpty
    private String building;
    private String roomNr;
    private Integer floor;
    private String description;
    @CreatedDate
    private Timestamp dateCreated;
    @LastModifiedDate
    private Timestamp dateUpdated;
    @Version
    private Integer version;


    public Location() {}

    public Location(String locationName, String place, String building, String roomNr, Integer floor, String description) {
        this.locationName = locationName;
        this.place = place;
        this.building = building;
        this.roomNr = roomNr;
        this.floor = floor;
        this.description = description;
    }

    public Location(String locationName, String place, String building) {
        this.locationName = locationName;
        this.place = place;
        this.building = building;
    }
}
