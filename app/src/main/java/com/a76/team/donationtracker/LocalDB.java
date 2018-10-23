package com.a76.team.donationtracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalDB { //TEMPORARY REPLACE WITH FIREBASE
    public static List<Location> locations = new ArrayList<>();
    public static Map<String, User> users = new HashMap<>();
    public static Map<Location, List<DonationItem>> donationItems = new HashMap<>();
}
