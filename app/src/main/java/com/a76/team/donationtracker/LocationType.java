package com.a76.team.donationtracker;

import java.io.IOException;

public enum LocationType {
    STORE ("Store"),
    DROP_OFF ("Drop off"),
    WAREHOUSE ("Warehouse");

    private final String type;

    LocationType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
