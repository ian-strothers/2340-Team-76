package com.a76.team.donationtracker;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserPasswordTest {
    private User testUser;
    @Before
    /**
     * Sets up User with
     * user.name "TestName"
     * user.userName "TestUserName"
     * user.password "TestPassword"
     * user.type "USER"
     */
    public void setUp() {
        testUser = new User("TestName", "TestUserName", "TestPassword", UserType.USER);
    }

    /**
     * Tests if true is returned if the correct password and usertype is given
     */
    @Test
    public void passwordAndTypeCorrectTest() {
        assertTrue(testUser.checkUser("TestPassword", UserType.USER));
    }

    /**
     * Tests if false is returned if wrong password but correct usertype is given
     */
    @Test
    public void passwordIncorrectTest() {
        assertEquals(false, testUser.checkUser("WRONG", UserType.USER));
    }

    /**
     * Tests if false is returned if correct password but wrong usertype is given
     */
    @Test
    public void typeIncorrectTest() {
        assertFalse(testUser.checkUser("TestPassword", UserType.ADMIN));
        assertFalse(testUser.checkUser("TestPassword", UserType.EMPLOYEE));
    }

    /**
     * Tests if falss is returned if wrong password and wrong usertype is given
     */
    @Test
    public void passwordAndTypeIncorrectTest() {
        assertFalse(testUser.checkUser("WrongPassword", UserType.EMPLOYEE));
        assertFalse(testUser.checkUser("WrongPassword", UserType.ADMIN));
    }
}