package com.a76.team.donationtracker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.Serializable;


/**
 * Student tests for sorting algorithms.
 *
 * @author Chaudhary Muhammad Talha Ahsan
 * @version 1.0
 */
public class DonationItemTester {
    private String name;
    private String timestamp;
    private Location location;
    private String description;
    private String shortDescription;
    private float value;
    private ItemType category;
    private String comments;
    private DonationItem successfulItem;
    private DonationItem newItem;

    @Before
    public void setUp() {
        name = "random name";
        timestamp = "random time stamp";
        description = "random description";
        shortDescription = "random short description";
        value = 1;
        category = ItemType.CLOTHING;
        comments = "random comments";
        location = new Location();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameError() {
        DonationItem newItem = new DonationItem(null, timestamp, location, description,
                shortDescription, value, category,
                comments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameError2() {
        DonationItem newItem = new DonationItem("", timestamp, location, description,
                shortDescription, value, category,
                comments);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testTimeStamp() {
        DonationItem newItem = new DonationItem(name, null, location, description,
                shortDescription, value, category,
                comments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTimeStamp2() {
        DonationItem newItem = new DonationItem(name, "", location, description,
                shortDescription, value, category,
                comments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLocation() {
        DonationItem newItem = new DonationItem(name, timestamp, null, description,
                shortDescription, value, category,
                comments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDescription() {
        DonationItem newItem = new DonationItem(name, timestamp, location, null,
                shortDescription, value, category,
                comments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDescription2() {
        DonationItem newItem = new DonationItem(name, timestamp, location, "",
                shortDescription, value, category,
                comments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShortDescription() {
        DonationItem newItem = new DonationItem(name, timestamp, location, description,
                null, value, category,
                comments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShortDescription2() {
        DonationItem newItem = new DonationItem(name, timestamp, location, description,
                "", value, category,
                comments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValue() {
        DonationItem newItem = new DonationItem(name, timestamp, location, description,
                shortDescription, 0.0f, category,
                comments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testComments() {
        DonationItem newItem = new DonationItem(name, timestamp, location, description,
                shortDescription, value, category,
                null);
    }

    @Test
    public void testSuccessful() {
        successfulItem = new DonationItem(name, timestamp, location, description,
                shortDescription, value, category,
                comments);
        Assert.assertEquals(successfulItem.getName(), name);
        Assert.assertEquals(successfulItem.getTimestamp(), timestamp);
        Assert.assertEquals(successfulItem.getLocation(), location);
        Assert.assertEquals(successfulItem.getDescription(), description);
        Assert.assertEquals(successfulItem.getShortDescription(), shortDescription);
        Assert.assertEquals(successfulItem.getValue(), value, 0.1);
        Assert.assertEquals(successfulItem.getCategory(), category);
        Assert.assertEquals(successfulItem.getComments(), comments);
    }
}



