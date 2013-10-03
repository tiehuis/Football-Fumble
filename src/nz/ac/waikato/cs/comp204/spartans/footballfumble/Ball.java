/*
 * File: Ball.java - A Ball class
 *
 * OD: 'Name'
 * Copyright: Spartans, 23/08/13++
 * License: GNU GPL v2
 * 
 * Notes: This file is used to instantiate a Ball
 * Issues: This program may be redundant
 * Reference: Sprite
 * Implements:
 * 
 */

package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 *  A Ball class.
 * 
 * 	Holds a constant for the mass of the ball which is
 * 	passed to the superclass upon instantiation of a new Ball.
 * @author Spartans
 * */
public class Ball extends Sprite{
	
	// If ball bounces of wall it moves indefinitely, but not if colliding with player
	
	private static int BALL_MASS = 5;
	
	/**
 	* Initializes the Ball instance using {@link 
 	* nz.ac.waikato.cs.comp204.spartans.footballfumble.Sprite#Sprite(int, int, int, int, DrawView, Bitmap, double)}
 	* with an initial speed of (0,0).
 	* @param initX the initial x position
 	* @param initY the initial y position
 	* @param drawView the view the ball will exist
 	* @param bitmap the bitmap to draw the ball on
 	* @author 'Name'
 	*/
	public Ball(int initX, int initY, DrawView drawView, Bitmap bitmap) {		
		super(initX, initY, 0, 0, drawView, bitmap, BALL_MASS);
	}
	
	protected void update(){
		
		// When the ball hits the edge of the screen, make it bounce a bit farther so it is easier for the player to hit
		if(!inxBounds()){
			setxSpeed(getxSpeed() * -1);
		}
		if(!inyBounds()){
			setySpeed(getySpeed() * -1);
		}	
		
		
		if(x > drawView.getWidth() - bitmap.getWidth() - xSpeed){
			xSpeed = xSpeed * -1;
		}
		if(x + xSpeed < 0){
			xSpeed = xSpeed * -1;
		}
		if(y > drawView.getHeight() - bitmap.getHeight() - ySpeed){
			ySpeed = ySpeed * -1;
		}
		if((y + ySpeed) < 0){
			ySpeed = ySpeed * -1;
		}
		
			
		x += xSpeed;
		y += ySpeed;
	}
	
	public void onDraw(Canvas canvas){
		
		// Slow down the ball before it is drawn
		setxSpeed(getxSpeed() * .95);
		setySpeed(getySpeed() * .95);
		
		// Draw the rectangle just a bit smaller than the overall bitmap
		rect.set(x+2, y+2, x+bitmap.getWidth()-2, y+bitmap.getHeight()-2);
		canvas.drawRect(rect, paint);
		
		canvas.drawBitmap(bitmap, x, y, null);
		
		update();
	}
	
	public boolean inyBounds(){
		if(y+60 > drawView.getHeight() - bitmap.getHeight() - getySpeed()){
			return false;
		}
		if(y-60 + getySpeed() < 0){
			return false;
		}
		return true;
	}

	public boolean inxBounds(){
		if(x-20 > drawView.getWidth() - bitmap.getWidth() - getxSpeed()){
			return false;
		}
		if(x + getxSpeed() < 20){
			return false;
		}		
		return true;	
	}
	
}
