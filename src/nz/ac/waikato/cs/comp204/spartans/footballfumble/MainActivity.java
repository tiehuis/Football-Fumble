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

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import android.app.Activity;

/**
 * The entry point for the program. Runs the initial code to start the program and set key
 * elements of the game in place. This includes the contentView, windowFlags and the {@link nz.ac.waikato.cs.comp204.spartans.footballfumble.DrawView}.
 * @author Spartans
 */
public class MainActivity extends Activity {

    BackgroundSound mBackgroundSound = new BackgroundSound();
    MediaPlayer player;
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
	}
	
	protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        mBackgroundSound.execute();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(player.isPlaying()){
        	player.stop();
        	player.release();
        }
    }
        // Another activity is taking focus (this activity is about to be "paused").
    
    @Override
    protected void onStop() {
        super.onStop();
    }
        // The activity is no longer visible (it is now "stopped")
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }  
    public class BackgroundSound extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... params) {     

            player = MediaPlayer.create(MainActivity.this,  R.raw.background2);
            player.setLooping(true); // Set looping 
            player.setVolume(100,100); 
            player.start(); 

            return null;
        }	
	}
 }

    