package com.a76.team.donationtracker;

public enum UserType {
    USER ("User"),
    EMPLOYEE ("Employee"),
    ADMIN ("Admin");

    private final String type;

    UserType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
