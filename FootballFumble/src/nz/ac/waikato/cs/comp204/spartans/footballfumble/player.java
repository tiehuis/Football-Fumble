/*
 * File: Player.java - A Player class
 *
 * OD: 'Name'
 * Copyright: Spartans, 28/08/13++
 * License: GNU GPL v2
 * 
 * Notes: This program holds the methods associated with the player class and also a player.
 * Issues: 
 * Reference: Sprite
 * Implements:
 * 
 */

package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;

/**
 *  A Player class.
 * 
 * 	Listens for any touches on the screen and moves the player class towards it using {@link #update()}.
 *	@author Spartans
 * */
public class Player extends Sprite{
	
	private boolean isDown 				= false;
	private boolean firstTouch 			= false;
	private double touchX, touchY, speed;
	private static int TOP_SPEED		= 6;
	private static int PLAYER_MASS 		= 5;
	private static double ACCELERATION 	= 0.086;
	
	/**
	 * Initializes the Player instance using {@link 
 	 * nz.ac.waikato.cs.comp204.spartans.footballfumble.Sprite#Sprite(int, int, int, int, DrawView, Bitmap, double)}
 	 * with an initial speed of (6,6).
	 * 
	 * 
	 * @param initX
	 * @param initY
	 * @param drawView
	 * @param bitmap
	 */
	public Player(int initX, int initY, DrawView drawView, Bitmap bitmap) {
		super(initX, initY, 6, 6, drawView, bitmap, PLAYER_MASS);
	}
	
	/**
	 * Checks for touches on the screen.
	 * <p>
	 * If a current touch is down, a direct path will be calculated for the player sprite to move toward. Each
	 * update a new sub-triangle is calculated and the x and y directional speeds are changed so that they
	 * keep following the current touch. 
	 * <p>
	 * If there is no touch the player will decelerate and eventually come to a halt.
	 */
	protected void update(){
		if(firstTouch)
		{
			double distance, trigX, trigY, theta; 
			
			// If a touch is on the screen accelerate the player towards the touch position.
			if(isDown){
				if(xSpeed <= TOP_SPEED){
					speed += ACCELERATION;
					if(speed > TOP_SPEED){
						speed = TOP_SPEED;
					}
				}
			}
			
			// A touch is not on the screen so decelerate the player.
			else{
				if(speed > 0){
					speed -= ACCELERATION;
					if(speed <= 0){
						speed = 0;
						return;
					}
				}
			}
			
			// Calculate a new sub-triangle for the player to follow.
			trigX = touchX - x;
			trigY = touchY - y;
			distance = Math.sqrt((trigX * trigX) + (trigY * trigY));
			theta = Math.acos(trigX / distance);			
			distance = distance / speed;
			trigX = distance * Math.cos(theta);
			trigY = distance * Math.sin(theta);
			
			// Adjust for the inverted y-axis.
			x += trigX;
			if(touchY > y){
				y += trigY;
			}else{
				y -= trigY;
			}			
		}
	
		// Is there a better way to layout all this.
		drawView.setOnTouchListener(new View.OnTouchListener(){
		
			public boolean onTouch(View v, MotionEvent e) 
			{
				firstTouch = true;
				touchX = e.getX();
				touchY= e.getY();
				switch (e.getAction()){		
					case MotionEvent.ACTION_DOWN:
						isDown = true;
						break;
					case MotionEvent.ACTION_MOVE:
						isDown = true;	
						break;		            		
					case MotionEvent.ACTION_UP:
						isDown = false;
						break;
		     		case MotionEvent.ACTION_OUTSIDE:
						isDown = false;
						break;
				}				
				return true;	
			}
		});
	}
}
	


