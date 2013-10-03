/*
 * File: MatchGlobal.java - A global class which holds all the static variables for each match.
 *
 * OD: Spartans
 * Copyright: Spartans, 28/08/13++
 * License: GNU GPL v2
 * 
 * Notes: This program stores all static variables for a match so other classes can access easily.
 * Issues: 
 * Reference:
 * Implements:
 */

package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import java.util.Timer;

public class MatchGlobal {
	
	public static Ball matchBall;
	public static Player player;
	public static AI ai;
	public static Timer matchTimer = new Timer();

	public static void matchStart(){
		// Start timer here
	}
	
}
