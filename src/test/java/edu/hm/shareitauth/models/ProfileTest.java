package edu.hm.shareitauth.models;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for class Profile.
 * @author Thomas Murschall
 *
 */
public class ProfileTest {

    private Profile profile = new Profile(false, "DE", "no-Adds");
    private Profile other = new Profile(true, "DE", "no-Adds");
    /**
     * Test for hash function.
     * @throws Exception 
     */
    @Test
    public void hashCodeTest() throws Exception {
        assertTrue(profile.hashCode() == "DE".hashCode() + "no-Adds".hashCode());
        assertTrue(other.hashCode() == "DE".hashCode() + "no-Adds".hashCode() * 2);
    }
    
    /**
     * Test for equals method.
     * @throws Exception 
     */
    @Test
    public void equalsTest() throws Exception {
        assertTrue(profile.equals(profile));
        assertFalse(profile.equals(new Profile(true, "DE", "no-Adds")));
        assertFalse(profile.equals(new Profile(false, "other", "no-Adds")));
        assertFalse(profile.equals(new Profile(false, "DE", "others")));
    }
    
    /**
     * Test for toSTring method.
     * @throws Exception 
     */
    @Test
    public void toStringTest() throws Exception {
        assertTrue("Profile{admin=false, language='DE', settings='no-Adds'}".equals(profile.toString()));       
    }
}
