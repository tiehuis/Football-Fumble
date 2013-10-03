/*
 * File: AI.java - An AI class
 *
 * OD: Spartans
 * Copyright: Spartans, 28/08/13++
 * License: GNU GPL v2
 * 
 * Notes: This program holds the methods associated with the AI class.
 * Issues: This class is derived from player, and needs to be refined.
 * Reference: Sprite, Player
 * Implements:
 */

package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 *  A simple AI class which gets the position of the ball and moves towards it.
 * 
 * 	Moves the AI sprite towards the ball on each call of {@link #update()}.
 *	@author Spartans
 * */
public class AI extends Sprite{

	private static int TOP_SPEED		= 5;
	private static int PLAYER_MASS 		= 70;
	private static double ACCELERATION 	= 2;		 //ensure acceleration is always positive
	private boolean inRange 			= false;
	private double dstX, dstY, speed; 			 //ensure speed is never negative.

	/**
	 * Initializes the Player instance using {@link 
 	 * nz.ac.waikato.cs.comp204.spartans.footballfumble.Sprite#Sprite(int, int, int, int, DrawView, Bitmap, double)}
 	 * with an initial speed of (6,6).
	 * @param initX the initial x position of this sprite.
	 * @param initY the initial y position of this sprite.
	 * @param drawView the view which this sprite is to be drawn on.
	 * @param bitmap the bitmap image associated with this sprite.
	 */
	public AI(int initX, int initY, DrawView drawView, Bitmap bitmap) {
		super(initX, initY, 2, 2, drawView, bitmap, PLAYER_MASS);
	}

	
	public void onDraw(Canvas canvas){
		
		this.rectPosX = x+10;
		this.rectPosY = y+50;
		this.rectRight = x+bitmap.getWidth()-10;
		this.rectBottom = y+bitmap.getHeight()-5;
		
		rect.set(rectPosX, rectPosY, rectRight, rectBottom);
		canvas.drawRect(rect, paint);
		
		canvas.drawBitmap(bitmap, x, y, null);
		
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		//canvas.drawLine(0, 0, this.getRectCenterX(), this.getRectCenterY(), paint);
		
		update();
	}
	
	
	/**
	 * Checks for the current position of the ball.
	 * <p>
	 * If a current touch is down, a direct path will be calculated for the ai sprite to move toward. Each
	 * update a new sub-triangle is calculated and the x and y directional speeds are changed so that they
	 * keep following the current ball position.
	 * <p>
	 */
	protected void update(){
		
		if(!inxBounds()){
			//setxSpeed(getxSpeed() * -1);
			if(x >= Match.ball.x){
				x -= this.xSpeed;
			}
			else{
				x += this.xSpeed;
			}
		}	
		else if(this.rectPosX < Match.ball.x){
			x += this.xSpeed;
			
		}
		else if(this.rectPosX > Match.ball.x){
			x -= this.xSpeed;
			
		}
		
		if(!inyBounds()){
			//setySpeed(getySpeed() * -1);
			if(y >= Match.ball.y){
				y -= this.ySpeed;
			}
			else{
				y += this.ySpeed;
			}
		}
		else if(this.rectPosY > Match.ball.y){
			y -= this.ySpeed;
		}
		else if(this.rectPosY < Match.ball.y){
			y += this.ySpeed;
		}
	}
	
	public boolean collides(Sprite sprite){
		
		if(this.rect.intersect(sprite.rect)){
			return true;
			
		}
		return false;	// there was no collision, therefore there is no current collision
	}
	
	public boolean inyBounds(){
		if(y+20 > drawView.getHeight() - bitmap.getHeight() - getySpeed()){
			return false;
		}
		if(y-20 + getySpeed() < 0){
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
	
	public int getRectCenterX(){
		return this.rectPosX + ((this.rectRight - this.rectPosX)/2);
	}
	
	public int getRectCenterY(){
		return this.rectPosY + ((this.rectBottom - this.rectPosY)/2);
	}
}
