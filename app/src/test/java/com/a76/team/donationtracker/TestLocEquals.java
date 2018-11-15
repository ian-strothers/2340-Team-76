package com.a76.team.donationtracker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestLocEquals {
    @Test
    public void testPointerEquality() {
        Location location = new Location();
        Location l2 = location;

        assertSame(location, l2);
        assertEquals(true, location.equals(l2));
    }

    @Test
    public void testEqualsNull() {
        Location location = new Location();

        assertEquals(false, location.equals(null));
    }

    @Test
    public void testEqualsNotInstanceOf() {
        Location location = new Location();
        Object o = new Object();

        assertEquals(false, location.equals(o));
    }

    @Test
    public void testDataEqualAndCommutitive() {
        Location l1 = new Location();
        l1.setName("name");
        l1.setLatitude(2.0f);
        l1.setLongitude(2.0f);
        l1.setAddress("address");
        l1.setPhone("8675309");
        l1.setType(LocationType.STORE);

        Location l2 = new Location();
        l2.setName("name");
        l2.setLatitude(2.0f);
        l2.setLongitude(2.0f);
        l2.setAddress("address");
        l2.setPhone("8675309");
        l2.setType(LocationType.STORE);

        assertEquals(true, l1.equals(l2));
        assertEquals(true, l2.equals(l1));
    }

    @Test
    public void testDataNotEqualAndCommutitive() {
        Location l1 = new Location();
        l1.setName("name");
        l1.setLatitude(2.0f);
        l1.setLongitude(2.0f);
        l1.setAddress("address");
        l1.setPhone("8675309");
        l1.setType(LocationType.STORE);

        Location l2 = new Location();
        l2.setName("mane");
        l2.setLatitude(2.0f);
        l2.setLongitude(2.0f);
        l2.setAddress("address");
        l2.setPhone("8675309");
        l2.setType(LocationType.STORE);

        assertEquals(false, l1.equals(l2));
        assertEquals(false, l2.equals(l1));

        l1 = new Location();
        l1.setName("name");
        l1.setLatitude(2.0f);
        l1.setLongitude(2.0f);
        l1.setAddress("address");
        l1.setPhone("8675309");
        l1.setType(LocationType.STORE);

        l2 = new Location();
        l2.setName("name");
        l2.setLatitude(1.0f);
        l2.setLongitude(2.0f);
        l2.setAddress("address");
        l2.setPhone("8675309");
        l2.setType(LocationType.STORE);

        assertEquals(false, l1.equals(l2));
        assertEquals(false, l2.equals(l1));

        l1 = new Location();
        l1.setName("name");
        l1.setLatitude(2.0f);
        l1.setLongitude(2.0f);
        l1.setAddress("address");
        l1.setPhone("8675309");
        l1.setType(LocationType.STORE);

        l2 = new Location();
        l2.setName("name");
        l2.setLatitude(2.0f);
        l2.setLongitude(1.0f);
        l2.setAddress("address");
        l2.setPhone("8675309");
        l2.setType(LocationType.STORE);

        assertEquals(false, l1.equals(l2));
        assertEquals(false, l2.equals(l1));

        l1 = new Location();
        l1.setName("name");
        l1.setLatitude(2.0f);
        l1.setLongitude(2.0f);
        l1.setAddress("address");
        l1.setPhone("8675309");
        l1.setType(LocationType.STORE);

        l2 = new Location();
        l2.setName("name");
        l2.setLatitude(2.0f);
        l2.setLongitude(2.0f);
        l2.setAddress("a dress");
        l2.setPhone("8675309");
        l2.setType(LocationType.STORE);

        assertEquals(false, l1.equals(l2));
        assertEquals(false, l2.equals(l1));

        l1 = new Location();
        l1.setName("name");
        l1.setLatitude(2.0f);
        l1.setLongitude(2.0f);
        l1.setAddress("address");
        l1.setPhone("8675309");
        l1.setType(LocationType.STORE);

        l2 = new Location();
        l2.setName("name");
        l2.setLatitude(2.0f);
        l2.setLongitude(2.0f);
        l2.setAddress("address");
        l2.setPhone("1800FLOWERS");
        l2.setType(LocationType.STORE);

        assertEquals(false, l1.equals(l2));
        assertEquals(false, l2.equals(l1));

        l1 = new Location();
        l1.setName("name");
        l1.setLatitude(2.0f);
        l1.setLongitude(2.0f);
        l1.setAddress("address");
        l1.setPhone("8675309");
        l1.setType(LocationType.STORE);

        l2 = new Location();
        l2.setName("name");
        l2.setLatitude(2.0f);
        l2.setLongitude(2.0f);
        l2.setAddress("address");
        l2.setPhone("8675309");
        l2.setType(LocationType.WAREHOUSE);

        assertEquals(false, l1.equals(l2));
        assertEquals(false, l2.equals(l1));
    }
}