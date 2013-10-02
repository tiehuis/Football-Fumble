/*
 * File: Sprite1.java - A dummy Sprite class which contains all the internal functionality of the class this is derived from (excluding draw methods), 
 * but does not require any drawView class to run correctly.
 *
 * OD: Spartans
 * Copyright: Spartans, 12/09/13++
 * License: GNU GPL v2
 * 
 * Notes: This file is used as a base for testing Sprite.java
 * Issues:
 * Reference: Sprite
 * Implements:
 */

package nz.ac.waikato.cs.comp204.spartans.tests;

import android.annotation.SuppressLint;

/**
 * A dummy Sprite class. Used to test the internal methods without requiring a specific drawView or bitmap. 
 * @author Spartans
 **/
public class Sprite1{
	
	protected double xSpeed, ySpeed, mass;
	protected int x, y;
	protected boolean currentCollision	= false;
	protected int scrHeight 			= 200;
	protected int scrWidth 				= 100;
	protected int bmpHeight 			= 5;
	protected int bmpWidth 				= 8;
		
	/**
	 * Initializes the Sprite instance.
	 * @param initX the initial x position of this sprite.
	 * @param initY the initial y position of this sprite.
	 * @param xSpd the initial x speed of this sprite.
	 * @param ySpd the initial y speed of this sprite.
	 * @param mass the mass of the sprite.
	 */
	public Sprite1(int initX, int initY, int xSpd, int ySpd, double mass){
		this.x 			= initX;
		this.y 			= initY;
		this.xSpeed		= xSpd;
		this.ySpeed 	= ySpd;
		this.mass 		= mass;
	}
	
	/**
	 * Checks the sprites position and moves it according to it's current bounds.
	 */
	protected void update(){
		if(!inxBounds()){
			x = scrWidth;
			setxSpeed((getxSpeed() + 3)*-1);
		}
		if(!inyBounds()){
			y = scrHeight;
			setySpeed((getySpeed() + 3)*-1);
		}		
		x += getxSpeed();
		y += getySpeed();
	}
	
	/**
	 * Moves the point by dx and dy.
	 * @deprecated the {@link #update()} method is used to move the object.
	 * @param dx the delta to move in the x direction.
	 * @param dy the delta to move in the y direction.
	 */
	@SuppressLint("WrongCall")
	public void move(int dx, int dy){
		this.x 		+= dx;
		this.y		+= dy;
	}
	
	/**
	 * Checks the collision status of this instance against another Sprite.
	 * @param sprite the Sprite being compared against this Sprite.
	 * @return true if a currentCollision is occurring, false otherwise
	 */
	public boolean collides(Sprite1 sprite){
		
		// If the sprite is already in a collision return false and deal with that first.
		if(currentCollision){
			return false;
		}
		
		// Case where s1 coming from top-left
		if(this.getLeft() <= sprite.getRight() && this.getTop() <= sprite.getBottom() && 
				this.getBottom() >= sprite.getBottom() && this.getRight() >= sprite.getRight()){
			currentCollision = true;
			return true;
		}
		
		// Case where s1 coming from top-right
		if(this.getLeft() <= sprite.getRight() && this.getTop() <= sprite.getBottom() 
				&& this.getRight() >= sprite.getLeft() && this.getBottom() >= sprite.getBottom()){
			currentCollision = true;
			return true;
		}
		
		currentCollision = false; // there was no collision, therefore there is no current collision
		return false;
	}
	
	/**
	 * Sets the new x and y directional speed.
	 * @param sprite the sprite that this instance is colliding with
	 */
	public void setDirection(Sprite1 sprite){
		setxSpeed(newxSpeed(sprite));
		setySpeed(newySpeed(sprite));
	}
	
	/**
	 * Calculates the new x directional speed based upon the mass of the two sprites that are colliding.
	 * @param sprite the sprite that this instance is colliding with
	 * @return the new x direction speed of this sprite
	 */
	public double newxSpeed(Sprite1 sprite){
		
		// Applies momentum to the objects. Player mass is 70, ball is 5. *7 slows initial speed
		return ((this.xSpeed * this.mass) + (sprite.xSpeed * sprite.mass))/(this.mass * 7);
	}
		
	/**
	 * Calculates the new y directional speed based upon the mass of the two sprites which are colliding.
	 * @param sprite the sprite that this instance is colliding with
	 * @return the new y direction speed of this sprite
	 */
	public double newySpeed(Sprite1 sprite){
		
		// Applies momentum to the objects. Player mass is 70, ball is 5. *7 slows initial speed
		return ((this.ySpeed * this.mass) + (sprite.ySpeed * sprite.mass))/(this.mass * 7);		
	}
	
	/**
	 * Checks if the current sprite is within the y-region bounded by the drawView height.
	 * @return false if out of y bound, otherwise true
	 */
	public boolean inyBounds(){
		// Bottom of screen
		if(y > this.scrHeight - this.bmpWidth - getySpeed()){
			return false;
		}
		// Top of screen
		if(y + getySpeed() < 35){
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if the current sprite is within the x-region bounded by the drawView width.
	 * @return false if out of x bound, otherwise true
	 */
	public boolean inxBounds(){
		// Right of screen
		if(x > this.scrWidth - this.bmpWidth - getxSpeed()){
			return false;
		}
		// Left of screen
		if(x + getxSpeed() < 0){
			return false;
		}		
		return true;	
	}
	
	/**
	 * @return the x position of the left of the bitmap image on canvas.
	 */
	public int getLeft(){
		return this.x;
	}
	
	/**
	 * @return the y position of the top of the bitmap image on canvas.
	 */
	public int getTop(){
		return this.y;
	}
	
	/**
	 * @return the x position of the right of the bitmap image on canvas.
	 */
	public int getRight(){
		return this.x + this.bmpWidth;
	}
	
	/**
	 * @return the y position of the bottom of the bitmap image on canvas,
	 */
	public int getBottom(){
		return this.y + this.bmpHeight;
	}

	/**
	 * @return the current speed in the x direction
	 */
	public double getxSpeed() {
		return xSpeed;
	}
		
	/**
 	* @return the current speed in the y direction
 	*/
	public double getySpeed() {
		return ySpeed;
	}

	/**
	 * @param xSpeed xSpeed to set (in pixels)
	 */
	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	/**
	 * @param ySpeed ySpeed to set (in pixels)
	 */
	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}
}