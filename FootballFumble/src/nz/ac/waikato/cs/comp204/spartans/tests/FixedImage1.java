/*
 * File: FixedImage1.java - A dummy FixedImage class which contains all the internal functionality of the class this is derived from (excluding draw methods), 
 * but does not require any drawView class to run correctly.
 *
 * OD: Spartans
 * Copyright: Spartans, 12/09/13++
 * License: GNU GPL v2
 * 
 * Notes: This file is used as a base for testing FixedImage.java
 * Issues:
 * Reference: Sprite1, FixedImage
 * Implements:
 */

package nz.ac.waikato.cs.comp204.spartans.tests;

/**
 * A dummy FixedImage Class. Used to test the internal methods without requiring a specific drawView or bitmap.
 * @author Spartans
 **/
public class FixedImage1 extends Sprite1{
	
	private static int FIXEDIMAGE_MASS	= 5;
	
	/**
	* Initializes the fixedImage using {@link 
 	* nz.ac.waikato.cs.comp204.spartans.tests.Sprite1#Sprite1(int, int, int, int, double)}
 	* with an initial speed of (0,0) and the mass stored in {@link #FIXEDIMAGE_MASS}.
 	* @param initX the initial x position
 	* @param initY the initial y position
 	*/
	public FixedImage1(int initX, int initY) {
		super(initX, initY, 0, 0, FIXEDIMAGE_MASS);
	}
}
