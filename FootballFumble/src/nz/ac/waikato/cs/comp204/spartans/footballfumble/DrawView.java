/*
 * File: DrawView.java - A DrawView Class
 *
 * OD: 'Name'
 * Copyright: Spartans, 23/08/13++
 * License: GNU GPL v2
 * 
 * Notes: This file is used to instantiate a Ball
 * Issues: This program may be redundant
 * Reference: View
 * Implements: 
 * 
 */

package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import java.util.ArrayList;
import java.util.Random;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import android.util.DisplayMetrics;

/**
 *  The drawView class.
 *	@author Spartans
 * */
public class DrawView extends View{

	private	Random 		random = new Random();
	private Ball 		ball;	
	private DisplayMetrics displayMetrics;
	private int screenWidth;
	private int screenHeight;
	private static ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
	
	/**
	 * @param context
	 */
	public DrawView(Context context) {
		super(context);	
		this.displayMetrics = context.getResources().getDisplayMetrics();
		this.screenHeight = displayMetrics.widthPixels;
		this.screenWidth = displayMetrics.heightPixels;
		setupField();
	}
	
	/**
	 *@param canvas
	 */
	@SuppressLint("WrongCall")
	@Override
	public void onDraw(Canvas canvas){
		
		// Draw every sprite onto the canvas
		for (Sprite sprite : spriteList){
			sprite.onDraw(canvas);
		}
		
		if(Math.abs(ball.getxSpeed()) < 0.1 || Math.abs(ball.getySpeed()) < 0.1){
			ball.setxSpeed(0);
			ball.setySpeed(0);
		}
		
		// When the ball is draw, slow the x and y speed. Should move out of the DrawView and into Sprite Update method.
		ball.setxSpeed(ball.getxSpeed() * .95);
		ball.setySpeed(ball.getySpeed() * .95);
		
		// Could place this into a separate method.
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
						}
						else if (sprite1 instanceof FixedImage){
							if (sprite2 instanceof Player){
								// what occurs to fixedimage after colliding with player
							}
							if (sprite2 instanceof Ball){
								// what occurs to fixedimage after colliding with ball
							}
						}
						else{ 
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
		// Put code that runs upon starting a match/socring a goal.
		// Could just remove draw list and reset the game each time, only keeping the...
		// ... score changes through the states.
		
		// Initialize some Sprite objects. Best to create and place this in a new method startUp().
		Bitmap bitmap1	= BitmapFactory.decodeResource(getResources(),  R.drawable.ball_25px);
		Bitmap bitmap2 	= BitmapFactory.decodeResource(getResources(),  R.drawable.player_50_75);
		Bitmap bitmap3	= BitmapFactory.decodeResource(getResources(),	R.drawable.cone_80_80);
		ball 			= new Ball(random.nextInt(600), random.nextInt(600), this, bitmap1);
		Player player1 	= new Player(300, 300, this, bitmap2);		//Need instantiation of classes, even if it says they are not explicitly used.
		FixedImage cone1 = new FixedImage(this.screenWidth/3-bitmap3.getWidth()/2, this.screenHeight/2-(bitmap3.getHeight()/2), this, bitmap3);
		FixedImage cone2 = new FixedImage(this.screenWidth/3-bitmap3.getWidth()/2, this.screenHeight/2-(bitmap3.getHeight()/2)+300, this, bitmap3);
		FixedImage cone3 = new FixedImage(this.screenWidth/3-bitmap3.getWidth()/2, this.screenHeight/2-(bitmap3.getHeight()/2)+600, this, bitmap3);
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

