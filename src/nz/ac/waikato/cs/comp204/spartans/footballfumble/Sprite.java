/*
 * File: Sprite.java - A Sprite Class
 *
 * OD: 'Name'
 * Copyright: Spartans, 23/08/13++
 * License: GNU GPL v2
 * 
 * Notes: This file is used to instantiate a Ball
 * Issues: This program may be redundant
 * Reference:
 * Implements:
 * 
 */

package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

/**
 *  A Sprite class.
 *  <p>
 *  To move x and y use {@link #move(Canvas, int, int)} and 
 *  to delete this instance from the spriteList use {@link #deleteInstance()}
 * 
 *	@author Spartans
 * */
public class Sprite{
	
	protected DrawView drawView;
	protected Bitmap bitmap;
	protected double xSpeed;
	protected double ySpeed;
	protected int x 					= 0;
	protected int y						= 0;
	protected boolean currentCollision	= false;
	protected Random random				= new Random();
	protected double mass 				= 0;
	
	protected Paint paint = new Paint();
	protected Rect rect = new Rect();
	protected int rectPosX, rectPosY, rectRight, rectBottom; 

	/**
	 * Initializes the Sprite instance.
	 * @param initX 	the initial x position
	 * @param initY 	the initial y position
	 * @param xSpd 		the initial x speed
	 * @param ySpd 		the initial y speed
	 * @param drawView 	the view which the object is to be drawn on
	 * @param bitmap 	the bitmap image associated with this sprite
	 * @param mass		the mass of the sprite
	 */
	public Sprite(int initX, int initY, int xSpd, int ySpd, DrawView drawView, Bitmap image, double mass){
		this.x 			= initX;
		this.y 			= initY;
		this.xSpeed		= xSpd;
		this.ySpeed 	= ySpd;
		this.drawView 	= drawView;
		this.bitmap 	= image;
		this.mass 		= mass;
		DrawView.addToList(this);
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.FILL);
		paint.setAlpha(0);
	}

	/**
	 * Checks the sprites position and runs the code each time {@link #onDraw(Canvas)} is called. 
	 */
	protected void update(){
		
		if(!inxBounds()){
			setxSpeed(getxSpeed() * -1);
		}
		if(!inyBounds()){
			setySpeed(getySpeed() * -1);
		}	
			
		x += getxSpeed();
		y += getySpeed();
	}
	
	/**
	 * Called whenever the canvas is invalidated. Updates the canvas with this objects bitmap. 
	 * Calls the {@link #update()} method.
	 * @param canvas handles what to draw
	 */
	public void onDraw(Canvas canvas){
		
		rect.set(x, y, x+bitmap.getWidth(), y+bitmap.getHeight());
		canvas.drawRect(rect, paint);
		
		canvas.drawBitmap(bitmap, x, y, null);
		
		update();
	}
	
	
	public boolean collides(Sprite sprite){
		
		if(this.rect.intersect(sprite.rect)){
	
			// If the sprite is already in a collision return false and deal with that first.
			if(currentCollision){
				return false;
			}
			return (currentCollision = true);
			
		}
		return (currentCollision = false);	// there was no collision, therefore there is no current collision
	}
	
	
	/**
	 * Sets the new x and y directional speed.
	 * @param sprite the sprite that this instance is colliding with
	 */
	public void setDirection(Sprite sprite){
		setxSpeed(newXSpeed(sprite));
		setySpeed(newYSpeed(sprite));
	}
	
	/**
	 * Calculates the new x directional speed based upon the mass of the two sprites that are colliding.
	 * @param sprite the sprite that this instance is colliding with
	 * @return the new x direction speed of this sprite
	 */
	public double newXSpeed(Sprite sprite){
		
		// Applies momentum to the objects. Player mass is 70, ball is 5. *5 slows initial speed
		return ((this.xSpeed * this.mass) + (sprite.xSpeed * sprite.mass))/(this.mass * 5);
	}
		
	/**
	 * Calculates the new y directional speed based upon the mass of the two sprites which are colliding.
	 * @param sprite the sprite that this instance is colliding with
	 * @return the new y direction speed of this sprite
	 */
	public double newYSpeed(Sprite sprite){
		
		// Applies momentum to the objects. Player mass is 70, ball is 5. *5 slows initial speed
		return ((this.ySpeed * this.mass) + (sprite.ySpeed * sprite.mass))/(this.mass * 5);		
	}
	
	/**
	 * Checks if the current sprite is within the y-region bounded by the drawView height.
	 * @return false if out of y bound, otherwise true
	 */
	public boolean inyBounds(){
		if(y > drawView.getHeight() - bitmap.getHeight() - getySpeed()){
			return false;
		}
		if(y + getySpeed() < 0){
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if the current sprite is within the x-region bounded by the drawView width.
	 * @return false if out of x bound, otherwise true
	 */
	public boolean inxBounds(){
		if(x > drawView.getWidth() - bitmap.getWidth() - getxSpeed()){
			return false;
		}
		if(x + getxSpeed() < 0){
			return false;
		}		
		return true;	
	}
	
	/**
	 * Deletes this instance from the spriteList, effectively removing the programs access to this instance
	 */
	public void deleteInstance(){
		DrawView.deleteFromList(this);
	}
	
	public int getLeft(){
		return this.x;
	}

	public int getTop(){
		return this.y;
	}

	public int getRight(){
		return this.x + bitmap.getWidth();
	}

	public int getBottom(){
		return this.y + bitmap.getHeight();
	}
	
	public int getCenterX(){
		return this.x + (bitmap.getWidth()/2);
	}
	
	public int getCenterY(){
		return this.y + (bitmap.getHeight()/2);
	}
	
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setX(int i){
		this.x = i;
	}
	
	public void setY(int i){
		this.y = i;
	}
	
	

	/**
	 * @return the width of bitmap image
	 */
	public int getWidth(){
		return bitmap.getWidth();
	}
	
	/**
	 * @return the height of the bitmap image
	 */
	public int getHeight(){
		return bitmap.getHeight();
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

