package org.elzacontiero.m3assessments.superheroes.dto;

import java.sql.Timestamp;

public class Recording {
    int id;
    int character_id;
    String address;

    // See http://wiki.gis.com/wiki/index.php/Decimal_degrees for required precision
    double latitude;
    double longitude;
    Timestamp timeOfSight;
}

