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
import android.view.Window;
import android.view.WindowManager;
import android.R.drawable;
import android.app.Activity;
import android.graphics.Color;

/**
 *  The MainActivity class.
 *
 *	Is the initial activity that is entered upon program start. The entry point for the program.
 *	@author Spartans
 * */
public class MainActivity extends Activity {
	
	DrawView drawView;
	
	/**
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // keeps the screen on

		Match match = new Match(this);
	}
}