package edu.hm.shareitauth.models;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for class User.
 * @author Thomas Murschall
 *
 */
public class UserTest {
    private User user = new User("user", "1234");
    
    /**
     * 
     * @throws Exception 
     */
    @Test
    public void toStringTest() throws Exception {
        assertTrue("User: user||Password: 1234".equals(user.toString()));
    }
}
