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

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.util.DisplayMetrics;

/**
 *  The drawView class.
 *	@author Spartans
 * */
public class DrawView extends View{

	public DisplayMetrics displayMetrics;
	public static int screenWidth, screenHeight;
	private static ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
	
	/**
	 * @param context
	 */
	public DrawView(Context context) {
		super(context);	
		this.displayMetrics = context.getResources().getDisplayMetrics();
		this.screenWidth = displayMetrics.widthPixels;
		this.screenHeight = displayMetrics.heightPixels;
		setupField();
	}
	
	
	/**
	 * Sets up the field with a default start state.
	 */
	public void setupField(){
		
		Bitmap goalImg	= BitmapFactory.decodeResource(getResources(),	R.drawable.goal_240_120);
		Bitmap ballImg	= BitmapFactory.decodeResource(getResources(),  R.drawable.ball_25px);
		Bitmap playerImg 	= BitmapFactory.decodeResource(getResources(),  R.drawable.player_50_75);
		Bitmap enemyImg 	= BitmapFactory.decodeResource(getResources(),  R.drawable.enemy_50_75);
		Bitmap coneImg	= BitmapFactory.decodeResource(getResources(),	R.drawable.cone_50_50);
		
		spriteList.clear();
		// Put code that runs upon starting a match/socring a goal.
		// Could just remove draw list and reset the game each time, only keeping the...
		// ... score changes through the states.
		
		// Initialize some Sprite objects. Best to create and place this in a new method startUp().
		Match.goal1				= new Goal((screenWidth/2)-(goalImg.getWidth()/2), 0, this, goalImg);
		Match.goal2				= new Goal((screenWidth/2)-(goalImg.getWidth()/2), screenHeight-goalImg.getHeight(), this, goalImg);
		Match.ball 				= new Ball(screenWidth/3, screenHeight/2, this, ballImg);
		Match.currentPlayer 	= new Player(300, 300, this, playerImg);		//Need instantiation of classes, even if it says they are not explicitly used.
		Match.playerAI 			= new AI(400, 500, this, enemyImg);
		
		Cone cone1 = new Cone(screenWidth/2-coneImg.getWidth()/2, screenHeight/2-(coneImg.getHeight()/2)-200, this, coneImg);
		//Cone cone2 = new Cone(screenWidth/2-coneImg.getWidth()/2, screenHeight/2-(coneImg.getHeight()/2), this, coneImg);
		Cone cone3 = new Cone(screenWidth/2-coneImg.getWidth()/2, screenHeight/2-(coneImg.getHeight()/2)+200, this, coneImg);
	}

	/**
	 *@param canvas
	 */
	@SuppressLint("WrongCall")
	@Override
	public void onDraw(Canvas canvas){
		
		// Draw every sprite onto the canvas
		for(Sprite sprite : spriteList){
			
			// Draw each sprite in the list
			sprite.onDraw(canvas);
			
			Paint text = new Paint();
			text.setColor(Color.WHITE);
			text.setTextSize(35);
			text.setTextAlign(Paint.Align.CENTER);
			
			canvas.drawText(""+Match.team1Score, Match.goal1.getCenterX(), Match.goal1.getCenterY(), text);
			canvas.drawText(""+Match.team2Score, Match.goal2.getCenterX(), Match.goal2.getCenterY(), text);
		}
		
		if(Math.abs(Match.ball.getxSpeed()) < 0.1 || Math.abs(Match.ball.getySpeed()) < 0.1){
			Match.ball.setxSpeed(0);
			Match.ball.setySpeed(0);
		}
		else{
			Match.ball.setxSpeed(Match.ball.getxSpeed() * .95);
			Match.ball.setySpeed(Match.ball.getySpeed() * .95);
		}
		/*
		if(Math.abs(Match.ball.getxSpeed()) < 0.5 || Math.abs(Match.ball.getySpeed()) < 0.5){
			Match.ball.setxSpeed(Match.ball.getxSpeed() * .95);
			Match.ball.setySpeed(Match.ball.getySpeed() * .95);
		}
		
		else{
			// THIS BIT WAS CAUSING THE BALL ROLLING PROBLEM:
			// If x or y speed is less than 0.3, stop the ball moving
			//Match.ball.setxSpeed(0);
			//Match.ball.setySpeed(0);
		}
		*/
		
		if(Match.goal1.isGoal()){
			Match.team1Score += 1;
			System.out.println("GOAL");
			Match.ball.x = screenWidth/2;
			Match.ball.y = screenHeight/2;
			Match.ball.xSpeed = 0;
			Match.ball.ySpeed = 0;
		}
		
		if(Match.goal2.isGoal()){
			Match.team2Score += 1;
			System.out.println("GOAL");
			Match.ball.x = screenWidth/2;
			Match.ball.y = screenHeight/2;
			Match.ball.xSpeed = 0;
			Match.ball.ySpeed = 0;
		}
		
		if(Match.goal1.collides(Match.ball)){
			System.out.println("Ball hit goal 1's post");
			Match.ball.setxSpeed(Match.ball.getxSpeed() * -1);
			Match.ball.setySpeed(Match.ball.getySpeed() * -1);
		}
		
		if(Match.goal2.collides(Match.ball)){
			System.out.println("Ball hit goal 2's post");
			Match.ball.setxSpeed(Match.ball.getxSpeed() * -1);
			Match.ball.setySpeed(Match.ball.getySpeed() * -1);
		}
		
		if(Match.currentPlayer.collides(Match.ball)){
			System.out.println("ball hit player");
			Match.ball.setDirection(Match.currentPlayer);
		}
		
		if(Match.playerAI.collides(Match.ball)){
			System.out.println("ball hit AI");
			Match.ball.setDirection(Match.playerAI);
		}
		
		for(Sprite p : spriteList){
			if(p instanceof Cone){
				if(p.collides(Match.ball)){
					System.out.println("ball hit a cone");
					Match.ball.setxSpeed(Match.ball.getxSpeed() * -1);
					Match.ball.setySpeed(Match.ball.getySpeed() * -1);
				}
			}
		}
		invalidate();
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

