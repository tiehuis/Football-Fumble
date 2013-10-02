/* File: Ball1Test.java - A test class to test the internal methods from Ball1.java.
 *
 * OD: Spartans
 * Copyright: Spartans, 12/09/13++
 * License: GNU GPL v2
 * 
 * Notes: This file contains the testing methods for Ball1.java
 * Issues: 
 * Reference: Ball1.java
 * Implements:
 */

package nz.ac.waikato.cs.comp204.spartans.tests;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * Holds the assertions and testing procedures for testing the class, Ball1.
 * @author Spartans
 */
public class Ball1Test {

	private static Ball1 ball;
	
	 /**
    * Run once for all tests before the first test executes.
    * Does nothing here.
    */
   @BeforeClass
   public static void setUpClass() {
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
    * Does nothing here.
    */
   @Before
   public void setUp() {
   }
   
   /**
    * Tests that the Ball1 is instantiated with the correct values.
    */
   @Test
   public void testInstantion(){
	   // Initialise Class and confirm values were set as intended.
	   ball = new Ball1(130, 100);
	   assertEquals("x not initialised correctly", 130, ball.x);
	   assertEquals("y not initialised correctly", 100, ball.y);
	   assertEquals("xSpeed not initialised correctly", 0, ball.xSpeed, 1e-6);
	   assertEquals("ySpeed not initialised correctly", 0, ball.ySpeed, 1e-6);     
	   assertEquals("mass not initialised correctly", 5, ball.mass, 1e-6);
   }
}