/*
 * File: MainActivity.java - The entry point of the program.
 *
 * OD: Spartans
 * Copyright: Spartans, 23/08/13++
 * License: GNU GPL v2
 * 
 * Notes: This file is the entry point for the program.
 * Issues:
 * Reference: Activity
 * Implements:
 */

package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.os.Bundle;
import android.view.WindowManager;
import android.app.Activity;

/**
 * The entry point for the program. Runs the initial code to start the program and set key
 * elements of the game in place. This includes the contentView, windowFlags and the {@link nz.ac.waikato.cs.comp204.spartans.footballfumble.DrawView}.
 * @author Spartans
 */
public class MainActivity extends Activity {
	
	/**
	 * The code which runs on startup of this activity.
	 * @param savedInstanceState the current state of this instance.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // keeps screen on
		
		DrawView drawView = new DrawView(this);		
		drawView.setBackgroundResource(R.drawable.pitch);					  // sets background to the pitch
		setContentView(drawView);
		
		try{
			if(Ball.load() != null)
				MatchGlobal.matchBall = Ball.load();
			if(Player.load() != null)
				MatchGlobal.player = Player.load();
			if(AI.load() != null)
				MatchGlobal.ai = AI.load();
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		try{
			if(Ball.load() != null)
				MatchGlobal.matchBall = Ball.load();
			if(Player.load() != null)
				MatchGlobal.player = Player.load();
			if(AI.load() != null)
				MatchGlobal.ai = AI.load();
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	@Override
	protected void onPause(){
		super.onPause();
		try{
			Ball.save(MatchGlobal.matchBall);
			Player.save(MatchGlobal.player);
			AI.save(MatchGlobal.ai);
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
}