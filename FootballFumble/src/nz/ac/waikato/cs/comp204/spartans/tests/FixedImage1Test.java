/*
 * File: FixedImage1Test.java - A test class to test the internal methods from Ball1.java.
 *
 * OD: Spartans
 * Copyright: Spartans, 12/09/13++
 * License: GNU GPL v2
 * 
 * Notes: This file contains the testing methods for FixedImage1.java
 * Issues: 
 * Reference: FixedImage1.java
 * Implements:
 */

package nz.ac.waikato.cs.comp204.spartans.tests;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * Holds the assertions and testing procedures for testing the class, FixedImage1.
 * @author Spartans
 */
public class FixedImage1Test {

	private static FixedImage1 fixedImage;
	
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
    * Tests that the FixedImage1 is instantiated with the correct values.
    */
   @Test
   public void testInstantion(){
	   // Initialise Class and confirm values were set as intended.
	   fixedImage = new FixedImage1(130, 100);
	   assertEquals("x not initialised correctly", 130, fixedImage.x);
	   assertEquals("y not initialised correctly", 100, fixedImage.y);
	   assertEquals("xSpeed not initialised correctly", 0, fixedImage.xSpeed, 1e-6);
	   assertEquals("ySpeed not initialised correctly", 0, fixedImage.ySpeed, 1e-6);     
	   assertEquals("mass not initialised correctly", 5, fixedImage.mass, 1e-6);
   }
}