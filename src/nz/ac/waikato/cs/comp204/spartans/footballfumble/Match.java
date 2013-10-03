package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import java.util.Timer;

import android.app.Activity;

public class Match {
	
	public static DrawView	drawView   = null;
	public static Timer 	matchTimer = new Timer();
	public static Ball 		ball   = null;
	public static Goal 		goal1, goal2   = null;
	public static Player	currentPlayer = null;
	public static AI		playerAI = null;
	public static int 		team1Score, team2Score = 0;

	
	PowerUpGenerator generator = new PowerUpGenerator();
	
	public Match(Activity activity){
		// Sets a new drawView with background yellow to occupy the whole available screen.
		drawView 	= new DrawView(activity);
		drawView.setBackgroundResource(R.drawable.pitch);
		activity.setContentView(drawView);
		generator.start();
	}
}
