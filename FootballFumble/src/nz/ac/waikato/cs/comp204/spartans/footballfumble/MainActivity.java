/*
 * File: MainActivity.java - Program Entry point
 *
 * OD: 'Name'
 * Copyright: Spartans, 15/06/13++
 * License: GNU GPL v2
 * 
 * Notes: This file is the entry point for the program
 * Issues:
 * Reference: Activity
 * Implements:
 * 
 */

package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.os.Bundle;
import android.view.WindowManager;
import android.app.Activity;
import android.graphics.Color;

/**
 *  The MainActivity class.
 * 
 *	Is the initial activity that is entered upon program start. The entry point for the program.
 * */
public class MainActivity extends Activity {
	
	DrawView drawView;
	
	/**
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // keeps the screen on
		
		// Sets a new drawView with background yellow to occupy the whole available screen.
		drawView 	= new DrawView(this);
		drawView.setBackgroundColor(Color.YELLOW);
		setContentView(drawView);
	}
}