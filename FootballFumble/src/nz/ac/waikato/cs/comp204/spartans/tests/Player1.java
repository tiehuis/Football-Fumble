/*
 * File: Player1.java - A dummy Player class which contains all the internal functionality of the class this is derived from (excluding draw methods), 
 * but does not require any drawView class to run correctly.
 *
 * OD: Spartans
 * Copyright: Spartans, 12/09/13++
 * License: GNU GPL v2
 * 
 * Notes: This file is used as a base for testing Player.java
 * Issues: 
 * Reference: Player
 * Implements:
 */

package nz.ac.waikato.cs.comp204.spartans.tests;

/**
 *  A dummy Player class. Used to test the internal methods without requiring a specific drawView or bitmap.
 *	@author Spartans
 * */
public class Player1{
	
	private static int TOP_SPEED		= 5;
	private static int PLAYER_MASS 		= 70;
	private static double ACCELERATION 	= 2;	
	private boolean isDown 				= false;
	private boolean firstTouch 			= false;	
	private int x, y;
	private double dstX, dstY, speed, xSpeed, ySpeed, distance, trigX, trigY, theta;
	
	/**
	 * Initializes the Player1 instance.
	 * @param initX the initial x position of this sprite.
	 * @param initY the initial y position of this sprite.
	 */
	public Player1(int initX, int initY) {
		this.x = initX;
		this.y = initY;
	}
	
	/**
	 * Each update a new sub-triangle is calculated and the x and y directional speeds are changed so that they
	 * keep following the current dstX and dstY coordinates.
	 * <p>
	 * If isDown is set to false, the player will decelerate and eventually come to a halt.
	 */
	protected void update(){
			
			// If a touch is on the screen accelerate the player towards the touch position.
			if(isDown){
				if(speed + ACCELERATION <= TOP_SPEED){
					speed += ACCELERATION;
				}else{
						speed = TOP_SPEED;
				}
			}else{ // a touch is not on the screen so decelerate the player.
				if((speed - ACCELERATION/5) >0){
					speed -= ACCELERATION/5;
				}else{
					speed = 0;
				}
			}
			// Calculate the appropriate triangle related to the touchx and touchy values
			trigX 		= Math.abs(dstX - x);
			trigY 		= Math.abs(dstY - y);
			distance	= Math.sqrt((trigX * trigX) + (trigY * trigY));
			theta 		= Math.acos(trigX / distance);	
			
			// If within 5 pixels of target, don't move. Due to the way android calculates
			// your current e.X and e.Y, there is a lot of variance and without this the player will vibrate underneath the finger.
			if (distance < 5){
				return;
			}
			
			// Calculating direction to move player
			if (dstX >= x){
				xSpeed		= speed * Math.cos(theta);
			} else {
				xSpeed 		= speed * Math.cos(theta + Math.PI);
			}
			
			if (dstY >= y){
				ySpeed		= speed * Math.sin(theta);
			} else {
				ySpeed		= speed * Math.sin(theta + Math.PI);
			}
			
			x 			+= xSpeed;
			y 			+= ySpeed;
	}
}