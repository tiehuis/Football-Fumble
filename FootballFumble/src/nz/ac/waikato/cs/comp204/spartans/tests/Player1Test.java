/*
 * File: Player1Test.java - A test class to test the internal methods from Player1.java.
 *
 * OD: Spartans
 * Copyright: Spartans, 12/09/13++
 * License: GNU GPL v2
 * 
 * Notes: This file contains the testing methods for FixedImage1.java
 * Issues: 
 * Reference: Player1.java
 * Implements:
 */

package nz.ac.waikato.cs.comp204.spartans.tests;

import static org.junit.Assert.*;
import java.lang.reflect.*; 
import org.junit.*;

/**
* Holds the assertions and testing procedures for testing the class, Player1.
*/
public class Player1Test{

	private static Player1 player;
	private static boolean isDown;
	private static double dstX;
	private static double dstY;
	private static double speed; //speed cannot be negative
	
	
	 /**
     * Run once for all tests before the first test executes.
     * Initialises an internal instance of Player which can be used for testing.
     * <p>
     * Note JUnit runs test in order of definition and we alter the state of the
     * instance with each test depending on the previous test in a chain here.
     *
     */
    @BeforeClass
    public static void setUpClass() {
        // Initialise Class and confirm *private* state
        player = new Player1(200, 300);
        assertEquals("x not initialised correctly", 200, get(player, "x"));
        assertEquals("y not initialised correctly", 300, get(player, "y"));
    }
    
    /**
     * Run once after all tests executed.
     * Does nothing here.
     */
    @AfterClass
    public static void tearDownClass() {}
       
    /**
     * Run after each test. Maybe discard play x,y here, but probably not necessary.
     * Does nothing here.
     */
    @After
    public void tearDown() {
    }
    
    /**
     * Run before each test. Set player reset x,y coordinates to position (200,300) and with a default speed of 2.
     */
    @Before
    public void setUp() {
    	set(player, "x", 200);
    	set(player, "y", 300);
    	set(player, "speed", 2);
    }
	
    
    //*** Testing Methods ***/
    /**
     * Tests {@link #Player#update()} with {@link #dstX} >> x and {@link #dstY} << y and isDown set to true. 1st Quadrant test.
     */
    @Test
	public void testUpdateTdownPdstxNdstY(){
    	set(player, "dstX", 230);
    	set(player, "dstY", 260);
    	set(player, "isDown", true);
    	player.update();
    	assertEquals("speed is not calculated correctly", 4, (Double)get(player, "speed"), 1e-6);
    	assertEquals("x is not calculated correctly", 202, get(player, "x"));
    	assertEquals("y is not calculated correctly", 296, get(player, "y"));
    	assertEquals("xSpeed is not calculated correctly", 2.4, (Double)get(player, "xSpeed"), 1e-6);
    	assertEquals("ySpeed is not calculated correctly", -3.2, (Double)get(player, "ySpeed"), 1e-6);
    	assertEquals("distance is not calculated correctly", 50, (Double)get(player, "distance"), 1e-6);
	}

	/**
     * Tests {@link #Player#update()} with {@link #dstX} << x and {@link #dstY} << y and isDown set to true. 2nd Quadrant test.
     */
    @Test
	public void testUpdateTdownNdstxNdstY(){
    	set(player, "dstX", 170);
    	set(player, "dstY", 260);
    	set(player, "isDown", true);
    	player.update();
    	assertEquals("speed is not calculated correctly", 4, (Double)get(player, "speed"), 1e-6);
    	assertEquals("x is not calculated correctly", 197, get(player, "x"));
    	assertEquals("y is not calculated correctly", 296, get(player, "y"));
    	assertEquals("xSpeed is not calculated correctly", -2.4, (Double)get(player, "xSpeed"), 1e-6);
    	assertEquals("ySpeed is not calculated correctly", -3.2, (Double)get(player, "ySpeed"), 1e-6);
    	assertEquals("distance is not calculated correctly", 50, (Double)get(player, "distance"), 1e-6);
	}
    
    /**
     * Tests {@link #Player#update()} with {@link #dstX} << x and {@link #dstY} >> y and isDown set to true. 3rd Quadrant test.
     */
    @Test
	public void testUpdateTdownNdstxPdstY(){
    	set(player, "dstX", 170);
    	set(player, "dstY", 340);
    	set(player, "isDown", true);
    	player.update();
    	assertEquals("speed is not calculated correctly", 4, (Double)get(player, "speed"), 1e-6);
    	assertEquals("x is not calculated correctly", 197, get(player, "x"));
    	assertEquals("y is not calculated correctly", 303, get(player, "y"));
    	assertEquals("xSpeed is not calculated correctly", -2.4, (Double)get(player, "xSpeed"), 1e-6);
    	assertEquals("ySpeed is not calculated correctly", 3.2, (Double)get(player, "ySpeed"), 1e-6);
    	assertEquals("distance is not calculated correctly", 50, (Double)get(player, "distance"), 1e-6);
	}
    
    /**
     * Tests {@link #Player#update()} with {@link #dstX} >> x and {@link #dstY} >> y and isDown set to true. 4th Quadrant test.
     */
    @Test
	public void testUpdateTdownPdstxPdstY(){
    	set(player, "dstX", 230);
    	set(player, "dstY", 340);
    	set(player, "isDown", true);
    	player.update();	
    	assertEquals("speed is not calculated correctly", 4, (Double)get(player, "speed"), 1e-6);
    	assertEquals("x is not calculated correctly", 202, get(player, "x"));
    	assertEquals("y is not calculated correctly", 303, get(player, "y"));
    	assertEquals("xSpeed is not calculated correctly", 2.4, (Double)get(player, "xSpeed"), 1e-6);
    	assertEquals("ySpeed is not calculated correctly", 3.2, (Double)get(player, "ySpeed"), 1e-6);
    	assertEquals("distance is not calculated correctly", 50, (Double)get(player, "distance"), 1e-6);
	}
    
    /**
     * Tests {@link #Player#update()} with {@link #dstX} >> x and {@link #dstY} << y and isDown set to false. 1st Quadrant test.
     */
    @Test
	public void testUpdateFdownPdstxNdstY(){
    	//
    	set(player, "dstX", 230);
    	set(player, "dstY", 260);
    	set(player, "isDown", false);
    	player.update();
    	assertEquals("speed is not calculated correctly", 1.6, (Double)get(player, "speed"), 1e-6);
    	assertEquals("x is not calculated correctly", 200, get(player, "x"));
    	assertEquals("y is not calculated correctly", 298, get(player, "y"));
    	assertEquals("xSpeed is not calculated correctly", 0.96, (Double)get(player, "xSpeed"), 1e-6);
    	assertEquals("ySpeed is not calculated correctly", -1.28, (Double)get(player, "ySpeed"), 1e-6);
    	assertEquals("distance is not calculated correctly", 50, (Double)get(player, "distance"), 1e-6);
	}
    
    /**
     * Tests {@link #Player#update()} with {@link #dstX} << x and {@link #dstY} << y and isDown set to false. 2nd Quadrant test.
     */
    @Test
	public void testUpdateFdownNdstxNdstY(){
    	set(player, "dstX", 170);
    	set(player, "dstY", 260);
    	set(player, "isDown", false);
    	player.update();
    	assertEquals("speed is not calculated correctly", 1.6, (Double)get(player, "speed"), 1e-6);
    	assertEquals("x is not calculated correctly", 199, get(player, "x"));
    	assertEquals("y is not calculated correctly", 298, get(player, "y"));
    	assertEquals("xSpeed is not calculated correctly", -0.96, (Double)get(player, "xSpeed"), 1e-6);
    	assertEquals("ySpeed is not calculated correctly", -1.28, (Double)get(player, "ySpeed"), 1e-6);
    	assertEquals("distance is not calculated correctly", 50, (Double)get(player, "distance"), 1e-6);
	}
    
    /**
     * Tests {@link #Player#update()} with {@link #dstX} << x and {@link #dstY} >> y and isDown set to false. 3rd Quadrant test.
     */
    @Test
	public void testUpdateFdownNdstxPdstY(){
    	set(player, "dstX", 170);
    	set(player, "dstY", 340);
    	set(player, "isDown", false);
    	player.update();
    	assertEquals("speed is not calculated correctly", 1.6, (Double)get(player, "speed"), 1e-6);
    	assertEquals("x is not calculated correctly", 199, get(player, "x"));
    	assertEquals("y is not calculated correctly", 301, get(player, "y"));
    	assertEquals("xSpeed is not calculated correctly", -0.96, (Double)get(player, "xSpeed"), 1e-6);
    	assertEquals("ySpeed is not calculated correctly", 1.28, (Double)get(player, "ySpeed"), 1e-6);
    	assertEquals("distance is not calculated correctly", 50, (Double)get(player, "distance"), 1e-6);
	}
    
    /**
     * Tests {@link #Player#update()} with {@link #dstX} >> x and {@link #dstY} >> y and isDown set to false. 4th Quadrant test.
     */
    @Test
	public void testUpdateFdownPdstxPdstY(){
    	set(player, "dstX", 230);
    	set(player, "dstY", 340);
    	set(player, "isDown", false);
    	player.update();	
    	assertEquals("speed is not calculated correctly", 1.6, (Double)get(player, "speed"), 1e-6);
    	assertEquals("x is not calculated correctly", 200, get(player, "x"));
    	assertEquals("y is not calculated correctly", 301, get(player, "y"));
    	assertEquals("xSpeed is not calculated correctly", 0.96, (Double)get(player, "xSpeed"), 1e-6);
    	assertEquals("ySpeed is not calculated correctly", 1.28, (Double)get(player, "ySpeed"), 1e-6);
    	assertEquals("distance is not calculated correctly", 50, (Double)get(player, "distance"), 1e-6);
	}
    
    /**
     * Tests {@link #Player#update()} with {@link #player#x} and {@link #player#y} within the circle of radius 5, origin centered at ({@link #dstX}, {@link #dstY}).
     */
    @Test
	public void testUpdateWithinRange(){
    	set(player, "dstX", 201);
    	set(player, "dstY", 299);
    	set(player, "isDown", false);
    	set(player, "xSpeed", 4);
    	set(player, "ySpeed", 3);
    	set(player, "speed", 4);
    	player.update();	
    	assertEquals("speed is not calculated correctly", 3.6, (Double)get(player, "speed"), 1e-6);
    	assertEquals("x is not calculated correctly", 200, get(player, "x"));
    	assertEquals("y is not calculated correctly", 300, get(player, "y"));
    	assertEquals("xSpeed is not calculated correctly", 4, (Double)get(player, "xSpeed"), 1e-6);
    	assertEquals("ySpeed is not calculated correctly", 3, (Double)get(player, "ySpeed"), 1e-6);
    	assertEquals("distance is not calculated correctly", Math.sqrt(2), (Double)get(player, "distance"), 1e-6);
	}
    
    /**
     * Tests {@link #Player#update()} with a negative {@link #speed} value. 
     */
    @Test
	public void testUpdateNegSpeed(){
    	//isDown false
    	set(player, "dstX", 230);
    	set(player, "dstY", 340);
    	set(player, "isDown", false);
    	set(player, "speed", -3);
    	player.update();
    	assertEquals("speed is not calculated correctly", 0, (Double)get(player, "speed"), 1e-6);
    	assertEquals("x is not calculated correctly", 200, get(player, "x"));
    	assertEquals("y is not calculated correctly", 300, get(player, "y"));
    	assertEquals("xSpeed is not calculated correctly", 0, (Double)get(player, "xSpeed"), 1e-6);
    	assertEquals("ySpeed is not calculated correctly", 0, (Double)get(player, "ySpeed"), 1e-6);
    	assertEquals("distance is not calculated correctly", 50, (Double)get(player, "distance"), 1e-6);
	}
    
    /**
     * Tests {@link #Player#update()} with a {@link #speed} which is greater than {@link #TOP_SPEED}. 
     */
    @Test
	public void testUpdateOverTopSpeed(){
    	set(player, "dstX", 230);
    	set(player, "dstY", 340);
    	set(player, "speed", 6);
    	set(player, "isDown", true);
    	player.update();
    	assertEquals("speed is not calculated correctly", 5, (Double)get(player, "speed"), 1e-6);
    	assertEquals("x is not calculated correctly", 203, get(player, "x"));
    	assertEquals("y is not calculated correctly", 304, get(player, "y"));
    	assertEquals("xSpeed is not calculated correctly", 3, (Double)get(player, "xSpeed"), 1e-6);
    	assertEquals("ySpeed is not calculated correctly", 4, (Double)get(player, "ySpeed"), 1e-6);
    	assertEquals("distance is not calculated correctly", 50, (Double)get(player, "distance"), 1e-6);
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

	/**
	 * Sets the value of a private data field.
	 *
	 * @param   o       the object instance to set the value in
	 * @param   member  the data field name to set the value of
	 * @param   value   the actual value to set the member to
	 * @author 	Simon Spacey
	 */
	private static void set(Object o, String member, Object value) {
		try {
			Field field = o.getClass().getDeclaredField(member);
			field.setAccessible(true);
			field.set(o, value);
		} catch (Exception e) {}
	}
}
