/*
 * File: Sprite1Test.java - A test class to test the internal methods from Sprite1.java.
 *
 * OD: Spartans
 * Copyright: Spartans, 12/09/13++
 * License: GNU GPL v2
 * 
 * Notes: This file contains the testing methods for Sprite1.java
 * Issues: 
 * Reference: Sprite1.java
 * Implements:
 */

package nz.ac.waikato.cs.comp204.spartans.tests;

import static org.junit.Assert.*;
import java.lang.reflect.*; 
import org.junit.*;

/**
* Holds the assertions and testing procedures for testing the class, Ball1.
*/
public class Sprite1Test {

	private static Sprite1 sprite;
	private static Sprite1 collide;
	
	 /**
     * Run once for all tests before the first test executes.
     * Initialises an internal instance of Sprite which can be used for testing.
     */
    @BeforeClass
    public static void setUpClass() {
        // Initialise Class and confirm *private* state
        sprite = new Sprite1(200, 300, 2, 3, 5);
        collide = new Sprite1(200, 300, 3, -2, 6);
        assertEquals("x not initialised correctly", 200, get(sprite, "x"));
        assertEquals("y not initialised correctly", 300, get(sprite, "y"));
        assertEquals("xSpeed not initialised correctly", 2, (Double)get(sprite, "xSpeed"), 1e-6);
        assertEquals("ySpeed not initialised correctly", 3, (Double)get(sprite, "ySpeed"), 1e-6);
        assertEquals("mass not initialise correctly", 5, (Double)get(sprite, "mass"), 1e-6);
    }
    
    /**
     * Run once after all tests executed.
     * Does nothing here.
     */
    @AfterClass
    public static void tearDownClass() {}
       
    /**
     * Run after each test.
     * Does nothing here.
     */
    @After
    public void tearDown() {
    }
    
    /**
     * Run before each test.
     * Resets the sprite values before each test is run.
     */
    @Before
    public void setUp() {
    	sprite.x = 200;
    	sprite.y = 300;
    	sprite.xSpeed = 2;
    	sprite.ySpeed = 3;
    	sprite.mass = 5;
    }
    
    
    //*** Testing Methods*/
    /**
     * Tests {@link #Sprite#move()} with positive deltas.
     */
    @SuppressWarnings("deprecation")
	@Test
    public void testMovePositive() {
        // Move the Point Instance and Check its Internal State;
        sprite.move(1, 2);
        assertEquals("x not set correctly", 201, get(sprite, "x"));
        assertEquals("y not set correctly", 302, get(sprite, "y"));
    }
    
    /**
     * Tests {@link #Sprite#move()} with negative deltas.
     */
    @SuppressWarnings("deprecation")
 	@Test
     public void testMoveNegative() {
         // Move the Point Instance and Check its Internal State;
         sprite.move(-1, -2);
         assertEquals("x not set correctly", 199, get(sprite, "x"));
         assertEquals("y not set correctly", 298, get(sprite, "y"));
     }
    
    /**
     * Tests {@link #Sprite#move()} with zero deltas.
     */
    @SuppressWarnings("deprecation")
 	@Test
     public void testMoveZero() {
         // Move the Point Instance and Check its Internal State;
         sprite.move(0, 0);
         assertEquals("x not set correctly", 200, get(sprite, "x"));
         assertEquals("y not set correctly", 300, get(sprite, "y"));
     }
    
    /**
     * Tests {@link #Sprite#newxSpeed()} ascribing a range, epsilon = 1e-6, for the actual value.
     */
    @Test
	public void testNewxSpeed(){
    	// Calculate the expected return value and check it against the actual value.
    	double value = sprite.newxSpeed(collide);
    	assertEquals("return value not calculated correctly", 0.8, value, 1e-6);
	}
    
    /**
     * Tests {@link #Sprite#newySpeed()} ascribing a range, epsilon = 1e-6, for the actual value.
     */
    @Test
	public void testNewySpeed(){
    	// Calculate the expected return value and check it against the actual value.
    	double value = sprite.newySpeed(collide);
    	assertEquals("return value not calculated correctly", 0.08571428571, value, 1e-6);
	}
    
    /**
     * Tests {@link #Sprite#setDirection()} using the collision occurring with the Sprite, 'collide'.
     */
    @Test
	public void testSetDirection(){
		sprite.setDirection(collide);
		assertEquals("xSpeed not set correctly", 0.8, (Double)get(sprite, "xSpeed"), 1e-6);
		assertEquals("ySpeed not set correctly", 0.08571428571, (Double)get(sprite, "ySpeed"), 1e-6);
	}
    
    /**
     * Tests {@link #Sprite#update()} with x and y both in current screen bounds.
     */
    @Test
	public void testUpdateinBounds(){
    	sprite.scrWidth = 200;
    	sprite.scrHeight = 300;
    	sprite.bmpWidth = 20;
    	sprite.bmpHeight = 20;
    	sprite.xSpeed = 5;
    	sprite.ySpeed = 5;
    	sprite.x = 150;
    	sprite.y = 250;
    	sprite.update();
    	assertEquals("x did not set to correct value", 155, sprite.x);
    	assertEquals("y did not set to correct value", 255, sprite.y);
	}
    
    /**
     * Tests {@link #Sprite#update()} with y in current screen bounds, but x not in screen bounds.
     */
    @Test
	public void testUpdateoutXBounds(){
    	sprite.scrWidth = 200;
    	sprite.scrHeight = 300;
    	sprite.bmpWidth = 20;
    	sprite.bmpHeight = 20;
    	sprite.xSpeed = 5;
    	sprite.ySpeed = 5;
    	sprite.x = 205;
    	sprite.y = 250;
    	sprite.update();
    	assertEquals("x did not set to correct value", 192, sprite.x);
    	assertEquals("y did not set to correct value", 255, sprite.y);
	}
    
    /**
     * Tests {@link #Sprite#update()} with x in current screen bounds, but y not in screen bounds.
     */
    @Test
	public void testUpdateoutYBounds(){
    	sprite.scrWidth = 200;
    	sprite.scrHeight = 300;
    	sprite.bmpWidth = 20;
    	sprite.bmpHeight = 20;
    	sprite.xSpeed = 5;
    	sprite.ySpeed = 5;
    	sprite.x = 150;
    	sprite.y = 367;
    	sprite.update();
    	assertEquals("x did not set to correct value", 155, sprite.x);
    	assertEquals("y did not set to correct value", 292, sprite.y);
	}
    
    /**
     * Tests {@link #Sprite#update()} with an x and y both not in current screen bounds.
     */
    @Test
	public void testUpdateoutXandYBounds(){
    	sprite.scrWidth = 200;
    	sprite.scrHeight = 300;
    	sprite.bmpWidth = 20;
    	sprite.bmpHeight = 20;
    	sprite.xSpeed = 5;
    	sprite.ySpeed = 5;
    	sprite.x = 789;
    	sprite.y = 305;
    	sprite.update();
    	assertEquals("x did not set to correct value", 192, sprite.x);
    	assertEquals("y did not set to correct value", 292, sprite.y);
	}
    
    
    /**
     * Tests {@link #Sprite#inxBounds()} with an x in current screen bounds.
     */
    @Test
	public void testInxBounds(){
    	sprite.scrWidth = 300;
    	sprite.scrHeight = 100;
    	sprite.bmpWidth = 40;
    	sprite.bmpHeight = 40;
    	sprite.xSpeed = 5;
    	sprite.x = 210;
    	assertTrue("InxBounds did not return correct value", sprite.inxBounds());
	}
    
    /**
     * Tests {@link #Sprite#inxBounds()} with an x not in current screen bounds.
     */
    @Test
	public void testOutxBounds(){
    	sprite.scrWidth = 205;
    	sprite.scrHeight = 100;
    	sprite.bmpWidth = 40;
    	sprite.bmpHeight = 40;
    	sprite.xSpeed = 5;
    	sprite.x = 210;
    	assertFalse("InxBounds did not return correct value", sprite.inxBounds());
	}
    
    /**
     * Tests {@link #Sprite#inyBounds()} with a y in current screen bounds.
     */
    @Test
	public void testInyBounds(){
    	sprite.scrWidth = 100;
    	sprite.scrHeight = 400;
    	sprite.bmpWidth = 40;
    	sprite.bmpHeight = 40;
    	sprite.xSpeed = 5;
    	sprite.y = 300;
    	assertTrue("InyBounds did not return correct value", sprite.inyBounds());
	}
    
    /**
     * Tests {@link #Sprite#inyBounds()} with a y not in current screen bounds.
     */
    @Test
	public void testOutyBounds(){
    	sprite.scrWidth = 100;
    	sprite.scrHeight = 200;
    	sprite.bmpWidth = 40;
    	sprite.bmpHeight = 40;
    	sprite.xSpeed = 5;
    	sprite.y = 300;
    	assertFalse("InyBounds did not return correct value", sprite.inyBounds());
	}
    
    /**
     * Tests {@link #Sprite#collides()} with currentCollision set to true (there already exists a collision being acted on between these objects).
     */
    @Test
	public void testCollidesCollisionT(){
    	sprite.currentCollision=true;
    	assertFalse("collides did not return the expected value", sprite.collides(collide));
	}
    
    /**
     * Tests {@link #Sprite#collides()} where the objects are not within collision range of each other. Assumes the Sprites do not exist in the same range before this test.
     */
    @Test
	public void testCollidesCollision(){
    	sprite.currentCollision=false;
    	assertFalse("collides did not return the correct value", sprite.collides(collide));
    }
    
    /**
     * Tests {@link #Sprite#collides()}
     */
    @Test
	public void testCollidesCollisionFtopright(){
    	sprite.currentCollision=false;
    	sprite.x = 5; // less than the following sum (two lines)
    	collide.x = 5; // +
    	collide.bmpWidth = 3;
    	
    	sprite.y = 5; // less than the following sum (two lines)
    	collide.y = 5; // +
    	collide.bmpWidth = 3;
    	
    	sprite.bmpHeight = 5;
    	sprite.bmpWidth = 5;
    	
    	assertTrue("collides did not return the expected value", sprite.collides(collide));
	}
    
    /**
     * Tests {@link #Sprite#collides()}
     */
    @Test
	public void testCollidesCollisionFtopleft(){
    	sprite.currentCollision=false;
    	sprite.x = 5; // less than the following sum (two lines)
    	collide.x = 5; // +
    	collide.bmpWidth = 3;
    	
    	sprite.y = 5; // less than the following sum (two lines)
    	collide.y = 5; // +
    	collide.bmpWidth = 3;
    	
    	sprite.bmpHeight = 5;
    	sprite.bmpWidth = 5;
    	
    	assertTrue("collides did not return the expected value", sprite.collides(collide));
	}
    
	//*** Private Access Methods ***/
	/**
	 * Returns the value of a private data field.
	 *
	 * @param   o       the object instance to get the value from
	 * @param   member  the data field name to get the value of
	 * @return  the value of the field or null if the field is not present or other error
	 * @author 	Simon Spacey
	 *
	 */
	private static Object get(Object o, String member) {
		try {
			Field field = o.getClass().getDeclaredField(member);
			field.setAccessible(true);
			return field.get(o);
		} catch (Exception e) {}
		return null;
	}
}

