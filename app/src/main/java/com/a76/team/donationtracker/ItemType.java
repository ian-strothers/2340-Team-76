package com.a76.team.donationtracker;

public enum ItemType {
    CLOTHING ("Clothing"),
    HAT ("Hat"),
    KITCHEN ("Kitchen"),
    ELECTRONICS ("Electronics"),
    HOUSEHOLD ("Household"),
    OTHER ("Other");

    private final String type;

    ItemType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
