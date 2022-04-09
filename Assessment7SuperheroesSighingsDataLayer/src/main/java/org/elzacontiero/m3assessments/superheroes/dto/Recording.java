package org.elzacontiero.m3assessments.superheroes.dto;

import java.sql.Timestamp;

public class Recording {
    long id;
    // Instead of getting the character id, we get the whole reference here.
    Character character;
    String address;

    // See http://wiki.gis.com/wiki/index.php/Decimal_degrees for required precision
    double latitude;
    double longitude;
    Timestamp timeOfSight;

    public Recording() { }

    public Recording(long id, Character character, String address, double latitude, double longitude,
            Timestamp timeOfSight) {
        this.id = id;
        this.character = character;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeOfSight = timeOfSight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Timestamp getTimeOfSight() {
        return timeOfSight;
    }

    public void setTimeOfSight(Timestamp timeOfSight) {
        this.timeOfSight = timeOfSight;
    }


}

