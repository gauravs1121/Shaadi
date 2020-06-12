package com.demo.shaadi.holder;

import android.location.Geocoder;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class LocationHolder {

    @Embedded(prefix = "street_")
    private StreetHolder street;
    private String city;
    private String state;
    private String country;
    private String postcode;


    @Embedded(prefix = "cord_")
    private CoordinatesHolder coordinates;
    @Embedded(prefix = "timezone_")
    private TimezoneHolder timezone;

    public StreetHolder getStreet() {
        return street;
    }

    public void setStreet(StreetHolder street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public CoordinatesHolder getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesHolder coordinates) {
        this.coordinates = coordinates;
    }

    public TimezoneHolder getTimezone() {
        return timezone;
    }

    public void setTimezone(TimezoneHolder timezone) {
        this.timezone = timezone;
    }


}
