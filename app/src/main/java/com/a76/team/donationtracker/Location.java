package com.a76.team.donationtracker;

import java.io.Serializable;

public class Location implements Serializable {
    private String name;
    private LocationType type;
    private double longitude;
    private double latitude;
    private String address;
    private String phone;

    public Location() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return location.longitude == longitude &&
                location.latitude == latitude &&
                name.equals(location.name) &&
                type == location.type &&
                address.equals(location.address) &&
                phone.equals(location.phone);
    }

    @Override
    public int hashCode() { //meh, it works
        return name.hashCode() + type.toString().hashCode()
                + (int) longitude + (int) latitude + address.hashCode() + phone.hashCode();
    }
}
