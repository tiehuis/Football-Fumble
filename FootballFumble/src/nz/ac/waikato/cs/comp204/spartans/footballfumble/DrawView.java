/*
 * File: DrawView.java - A DrawView Class
 *
 * OD: Spartans
 * Copyright: Spartans, 23/08/13++
 * License: GNU GPL v2
 * 
 * Notes: This file is used to instantiate a Ball
 * Issues:
 * Reference: View
 * Implements: 
 * 
 */

package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import android.util.DisplayMetrics;

/**
 *  This handles drawing of all objects on to screen.
 *	@author Spartans
 * */
public class DrawView extends View{

	private Ball ball;	
	private DisplayMetrics displayMetrics;
	private int screenWidth;
	private int screenHeight;
	private static ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
	private boolean fixedDisplayed = false;
	
	/**
	 * Passes the current context to the View superclass and gets the current screenHeight and Width of this context. 
	 * Also calls the {@link #setupField()} method.
	 * @param context the current application environment
	 */
	public DrawView(Context context) {
		super(context);	
		this.displayMetrics	 = context.getResources().getDisplayMetrics();
		this.screenHeight	 = displayMetrics.widthPixels;
		this.screenWidth	 = displayMetrics.heightPixels;
		setupField();
	}
	
	/**
	 * This method is called when the canvas state has changed. It uses the current canvas to draw all the sprite
	 * objects contained in {@link #spriteList} on to the canvas.
	 *@param canvas holds the current draw cells
	 */
	@SuppressLint("WrongCall")
	@Override
	public void onDraw(Canvas canvas){
		
		// Draw every sprite onto the canvas
		for (Sprite sprite : spriteList){
			sprite.onDraw(canvas);
		}
		// When the ball is draw, slow the x and y speed.
		if(Math.abs(ball.getxSpeed()) > 0.5 || Math.abs(ball.getySpeed()) > 0.5){
			ball.setxSpeed(ball.getxSpeed() * .95);
			ball.setySpeed(ball.getySpeed() * .95);

		}else{
			// If x or y speed is less than 0.3, stop the ball moving
			ball.setxSpeed(0);
			ball.setySpeed(0);
		}
		
		for (Sprite sprite1 : spriteList){
			for (Sprite sprite2 : spriteList){
				if (sprite1 != sprite2){
					if (sprite1.collides(sprite2)){
						if (sprite1 instanceof Player){				// what to do if player collides with another object
							if (sprite2 instanceof FixedImage){
								// what occurs to player after colliding with FixedImage
							}
							if (sprite2 instanceof Player){
								// what occurs to player after colliding with another Player
							}
							if (sprite2 instanceof Ball){
								// what occurs to player after colliding with ball
							}
						}else if (sprite1 instanceof FixedImage){
							if (sprite2 instanceof Player){
								// what occurs to fixedimage after colliding with player
							}
							if (sprite2 instanceof Ball){
								// what occurs to fixedimage after colliding with ball
							}
						}else{ 
							if (sprite2 instanceof Player){
								sprite1.setDirection(sprite2);		// if a ball collides with a player, set a new direction...
																	// ...based on the players mass and x,y.
							}
							if (sprite2 instanceof FixedImage){
								// what occurs to Ball after hitting a fixedimage
							}
						}
					}
				}		
			}
		}			
		invalidate();
	}	
	
	/**
	 * Sets up the field with a default start state.
	 */
	public void setupField(){
		
		spriteList.clear();
		
		// Initialize some Sprite objects.
		Bitmap bitmap1	= BitmapFactory.decodeResource(getResources(),  R.drawable.ball_25px);
		Bitmap bitmap2 	= BitmapFactory.decodeResource(getResources(),  R.drawable.player_50_75);
		Bitmap bitmap3	= BitmapFactory.decodeResource(getResources(),	R.drawable.cone_80_80);
		ball 			= new Ball(400, 1080, this, bitmap1);
		Player player1 	= new Player(400, 1150, this, bitmap2);
		
		if(!fixedDisplayed){			
			FixedImage cone1 = new FixedImage(this.screenWidth/3-bitmap3.getWidth()/2, this.screenHeight/2-(bitmap3.getHeight()/2), this, bitmap3);
			FixedImage cone2 = new FixedImage(this.screenWidth/3-bitmap3.getWidth()/2, this.screenHeight/2-(bitmap3.getHeight()/2)+300, this, bitmap3);
			FixedImage cone3 = new FixedImage(this.screenWidth/3-bitmap3.getWidth()/2, this.screenHeight/2-(bitmap3.getHeight()/2)+600, this, bitmap3);
			fixedDisplayed	 = true;
		}
	}
	
	/**
	 * @param sprite Sprite to add to {@link #spriteList}
	 */
	public static void addToList(Sprite sprite){
		spriteList.add(sprite);
	}
	
	/**
	 * @param sprite Sprite to delete from {@link #spriteList}
	 */
	public static void deleteFromList(Sprite sprite){
		spriteList.remove(sprite);
	}
}