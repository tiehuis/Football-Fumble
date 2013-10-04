/*
 * File: MenuActivity.java - The entry point of the program.
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

import nz.ac.waikato.cs.comp204.spartans.footballfumble.MainActivity.BackgroundSound;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * The entry point for the program. Runs the initial code to start the program and set key
 * elements of the game in place. This includes the contentView, windowFlags and the {@link nz.ac.waikato.cs.comp204.spartans.footballfumble.DrawView}.
 * @author Spartans
 */
public class MenuActivity extends Activity {
	/**
	 * The code which runs on startup of this activity.
	 * @param savedInstanceState the current state of this instance.
	 */
	
	 //BackgroundSound mBackgroundSound = new BackgroundSound();
	 //MediaPlayer player;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // keeps screen on
		
		DrawView drawView = new DrawView(this);
		drawView.setBackgroundColor(0);				  // sets background to the pitch
		setContentView(R.layout.activity_main);
	}
	
	public void startGame(View view) {
	    Intent intent = new Intent(MenuActivity.this, MainActivity.class);
	    startActivity(intent);
	}
	
	public void endGame(View view) {
		MenuActivity.this.finish();
		System.exit(0);
	}
	
	public void aboutGame(View view) {
		 
		// custom dialog
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.custom);
		dialog.setTitle("About Football Fumble");
		// set the custom dialog components - text, image and button
		TextView text = (TextView) dialog.findViewById(R.id.text);
		text.setText("To play, press the 'start game' button on the previous screen. When you touch the screen, the player will move towards your finger." +
				" Try to kick the ball into the goal before the opposition does! The game is arcade based, so the ball will bounce off the" +
				" walls. Have fun!");
		ImageView image = (ImageView) dialog.findViewById(R.id.image);
		image.setImageResource(R.drawable.ic_launcher);

		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		dialog.show();/*
		final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Reset...");
		alertDialog.setMessage("Are you sure?");
		alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			alertDialog.cancel();
		}
		});
		//alertDialog.setIcon(R.drawable.icon);
		alertDialog.show();*/
	  }
	
	protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        //mBackgroundSound.execute();
    }
    @Override
    protected void onPause() {
        super.onPause();
        /*
        if(player.isPlaying()){
        	player.stop();
        	player.release();
        }*/
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
    /*
    public class BackgroundSound extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... params) {     

            player = MediaPlayer.create(MenuActivity.this,  R.raw.background3);
            player.setLooping(true); // Set looping 
            player.setVolume(100,100); 
            player.start(); 

            return null;
        }	
	}*/
 }
