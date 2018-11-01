package com.a76.team.donationtracker;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class LocalDB { //TEMPORARY REPLACE WITH FIREBASE
    public static ArrayList<Location> locations = new ArrayList<>();
    public static HashMap<String, User> users = new HashMap<>();
    public static HashMap<Location, ArrayList<DonationItem>> donationItems = new HashMap<>();

    public static boolean loaded = false;

    //shhhhh its temporary
    public static void saveData(File f) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));

        out.writeObject(locations);
        out.writeObject(users);
        out.writeObject(donationItems);

        out.close();
    }

    public static void loadData(File f) throws IOException {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));

            locations = (ArrayList<Location>) in.readObject();
            users = (HashMap<String, User>) in.readObject();
            donationItems = (HashMap<Location, ArrayList<DonationItem>>) in.readObject();

            in.close();

            loaded = true;
        } catch (ClassNotFoundException e) {
            Log.e("Deserialization", "Critical failure in deserialization", e);
        }
    }
}
