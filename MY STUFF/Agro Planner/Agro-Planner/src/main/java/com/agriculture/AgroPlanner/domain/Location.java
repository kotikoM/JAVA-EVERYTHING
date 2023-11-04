package com.agriculture.AgroPlanner.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import static com.agriculture.AgroPlanner.constants.ColumnNames.LOCATION_LATITUDE;
import static com.agriculture.AgroPlanner.constants.ColumnNames.LOCATION_LONGITUDE;


@Embeddable
@SuppressWarnings("unused")
public class Location {
    @Column(name = LOCATION_LATITUDE)
    private Double latitude;

    @Column(name = LOCATION_LONGITUDE)
    private Double longitude;

    public Location(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location() {
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
