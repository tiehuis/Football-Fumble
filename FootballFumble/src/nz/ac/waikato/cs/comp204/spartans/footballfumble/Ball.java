/*
 * File: Ball.java - A Ball class
 *
 * OD: Spartans
 * Copyright: Spartans, 23/08/13++
 * License: GNU GPL v2
 * 
 * Notes: This file is used to instantiate a Ball
 * Issues: The ballSpeed is updated in the drawView and not here.
 * Reference: Sprite
 * Implements:
 */

package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.graphics.Bitmap;

/**
 *  A Ball class.
 * 
 * 	Holds a constant for the mass of the ball which is
 * 	passed to the superclass upon instantiation of a new Ball.
 *  @author Spartans
 * */
public class Ball extends Sprite{
	
	private static int BALL_MASS = 5;
	
	/**
 	* Initializes the Ball instance using {@link 
 	* nz.ac.waikato.cs.comp204.spartans.footballfumble.Sprite#Sprite(int, int, int, int, DrawView, Bitmap, double)}
 	* with an initial speed of (0,0) and the mass stored in {@link #BALL_MASS}.
 	* @param initX the initial x position
 	* @param initY the initial y position
 	* @param drawView the view the ball will exist
 	* @param bitmap the bitmap to draw the ball on
 	*/
	public Ball(int initX, int initY, DrawView drawView, Bitmap bitmap) {		
		super(initX, initY, 0, 0, drawView, bitmap, BALL_MASS);
	}
	
	public void update(){
		
		// When the ball is draw, slow the x and y speed.
		if(Math.abs(this.getxSpeed()) > 0.5 || Math.abs(this.getySpeed()) > 0.5){
			this.setxSpeed(ball.getxSpeed() * .95);
			this.setySpeed(ball.getySpeed() * .95);

		}else{
			// If x or y speed is less than 0.3, stop the ball moving
			this.setxSpeed(0);
			this.setySpeed(0);
		}
		
		if(!inxBounds()){
			x = drawView.getWidth();
			setxSpeed((getxSpeed() + 3) * -1);
		}
		if(!inyBounds()){
			y = drawView.getHeight();
			setySpeed((getySpeed() + 3) * -1);
		}	
		
		x += getxSpeed();
		y += getySpeed();
	}
}
