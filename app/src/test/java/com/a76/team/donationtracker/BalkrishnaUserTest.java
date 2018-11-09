package com.a76.team.donationtracker;
import org.junit.Test;
import static org.junit.Assert.*;

public class BalkrishnaUserTest {
    /*Test 0.0: Correct User is Created
    * this test will test that a user was created correctly*/
    @Test
    public void createCorrectUserTest() {
        User correctUser = new User("Bob", "Bob1", "password", UserType.USER);
        assertEquals("Bob", correctUser.getName());
        assertEquals("Bob1", correctUser.getUserName());
        assertEquals("password", correctUser.getPassword());
        assertEquals(UserType.USER, correctUser.getType());

    }
    /*Test 1.0: Null is passed in for Name
    * this test will throw an expection because null is passed in for the name*/
    @Test (expected = IllegalArgumentException.class)
    public void nullNamePassedInTest() {
        User nullNameUser = new User(null, "NullName1", "password", UserType.USER);
    }

    /*Test 1.1: Empty string is passed in for Name
    * this test will throw an exception because an empty string was passed in for name*/
    @Test (expected = IllegalArgumentException.class)
    public void emptyNamePassedInTest() {
        User emptyNameuser = new User("", "emptyName1", "password", UserType.USER);
    }

    /*Test 2.0: Null is passed in for Username
    * this test will throw an exception because null is passed in for the username*/
    @Test (expected = IllegalArgumentException.class)
    public void nullUserNamePassedInTest() {
        User nullUserNameUser = new User("nullUserName", null, "password", UserType.USER);
    }

    /*Test 2.1: Empty string is passed in for Username
     * this test will throw an exception because empty string is passed in for the username*/
    @Test (expected = IllegalArgumentException.class)
    public void emptyUserNamePassedInTest() {
        User emptyuserNameUser = new User("emptyUserName", "", "password", UserType.USER);
    }

    /*Test 3.0: Null is passed in for password
     * this test will throw an exception because null is passed in for the password*/
    @Test (expected = IllegalArgumentException.class)
    public void nullPasswordPassedInTest() {
        User nullPasswordUser = new User("nullPasswordUser", "nPU", null, UserType.USER);
    }

    /*Test 3.1: Empty string is passed in for password
     * this test will throw an exception because empty string is passed in for the password*/
    @Test (expected = IllegalArgumentException.class)
    public void emptyPasswordPassedInTest() {
        User emptyPasswordUser = new User("nullPasswordUser", "nPU", "", UserType.USER);
    }


}
