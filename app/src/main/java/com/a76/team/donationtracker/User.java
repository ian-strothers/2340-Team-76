package com.a76.team.donationtracker;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String userName;
    private String password;
    private UserType type;

    public User(String name, String userName, String password, UserType type) throws IllegalArgumentException {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Invalid Name");
        }

        if (userName == null || name.equals("")) {
            throw new IllegalArgumentException("Invalid Username");
        }

        if (password == null || name.equals("")) {
            throw new IllegalArgumentException("Invalid Password");
        }

        this.name     = name;
        this.userName = userName;
        this.password = password;
        this.type     = type;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public UserType getType() {
        return type;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof User)) {
            return false;
        }

        User o = (User) other;

        return this.name.equals(o.name) && this.userName.equals(o.userName)
                && this.password.equals(o.password) && this.type == o.type;
    }
}
