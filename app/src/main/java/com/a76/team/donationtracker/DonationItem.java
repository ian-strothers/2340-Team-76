package com.a76.team.donationtracker;

import java.io.Serializable;

public class DonationItem implements Serializable {
    private String timestamp;
    private Location location;
    private String description;
    private String shortDescription;
    private float value;
    private ItemType category;
    private String comments;

    public DonationItem(String timestamp, Location location, String description,
                        String shortDescription, float value, ItemType category,
                        String comments) throws IllegalArgumentException {
        if (timestamp == null || timestamp.equals("")) {
            throw new IllegalArgumentException("Invalid timestamp");
        }

        if (location == null) {
            throw new IllegalArgumentException("Invalid location");
        }

        if (description == null || description.equals("")) {
            throw new IllegalArgumentException("Invalid description");
        }

        if (shortDescription == null || shortDescription.equals("")) {
            throw new IllegalArgumentException("Invalid short desc");
        }

        if (value == 0.0f) {
            throw new IllegalArgumentException("Invalid value");
        }

        if (comments == null) {
            throw new IllegalArgumentException("Invalid comments");
        }

        this.timestamp = timestamp;
        this.location = location;
        this.description = description;
        this.shortDescription = shortDescription;
        this.value = value;
        this.category = category;
        this.comments = comments;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Location getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public float getValue() {
        return value;
    }

    public ItemType getCategory() {
        return category;
    }

    public String getComments() {
        return comments;
    }

    public String getLocationName() {
        return location.getName();
    }
}
